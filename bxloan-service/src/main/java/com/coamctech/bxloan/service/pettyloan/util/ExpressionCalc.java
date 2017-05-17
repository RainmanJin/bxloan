package com.coamctech.bxloan.service.pettyloan.util;

import java.util.ArrayList;
import java.util.List;

import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;


/**
 * <p>Description:混合四则运算表达式解析、求值类</p>
 * <p>Copyright:(c) 2005~2006</p>
 * <p>Company: Wuhan VTeamSystem Co.Ltd.</p>
 * EDIT HISTORY：<br>
 * [1.0] 2005/12/19 javarain 基础类整理<br>
 */

/**
 * 功能：
 * 1、混合四则运算；
 * 2、只能包括括号和加减乘除运算；
 * 3、中间可以留空格；
 * 4、可以重新设置运算量，并重新获得表达式
 *
 * 算法说明：
 * 1、按照从左至右的顺序依次处理表达式各元素；
 * 2、在处理过程中，每次分别将一个元素入栈
 * 	  如果是运算量元素，放入运算量堆栈；
 * 	  如果是运算符元素，放入运算符堆栈；
 * 3、设定运算符的优先级别依次从高到低为： *  /  +  -  );左括号有特别之处，需要另外判断
 * 4、当入栈的时候，需要判断运算符变量，
 * 	  如果栈顶的运算符比要入栈的运算符优先级高，则入栈；
 *    反之则进行出栈计算，即取栈顶的运算符和两个运算量运算的结果作为要新运算量入栈；
 * 	  特别的，如果运算符栈顶是左括号或没有元素，或要入栈的是左括号，上面的规则无效，运算符可以入栈
 * 5、如果右括号入栈，同时栈顶为左括号，则左括号出栈，右括号不入栈。
 */

public class ExpressionCalc{

	private  final String FLAG_TRUE = "1"; // "真"
	private  final String FLAG_FALSE = "0"; // "假"
	
  private ArrayList number; //按照从左至右顺序存放运算量,因该是最初的运算量模板
  private ArrayList number_value; //这是设置了真正数值的运算量
  private ArrayList operator; //按照从左至右顺序存放运算符
  private ArrayList all; //按照从左至右表达式(运算量,运算符)
  private String exp; //存放要求值的表达式

  private java.util.Stack nstack; //运算量堆栈
  private java.util.Stack ostack; //运算符堆栈

  //定义运算符
  private final String OPER_SUB="-";
  private final String OPER_ADD="+";
  private final String OPER_NEG="#"; //表示特殊的情况即负号的情况，它的优先级应该大于加号和减号
  private final String OPER_DIV="/";
  private final String OPER_MUL="*";
  private final String OPER_LEFT_BRACKET="(";
  private final String OPER_RIGHT_BRACKET=")";

  //定义运算符的优先级
  private final int PRI_SUB=1;
  private final int PRI_ADD=1;
  private final int PRI_NEG=5;
  private final int PRI_DIV=5;
  private final int PRI_MUL=5;
  private final int PRI_RIGHT_BRACKET=0; //优先级最低
  private final int PRI_LEFT_BRACKET=-999;

  /**
   * 构造器
   */
  public ExpressionCalc(){
    this.exp=null;
  }

  /**
   *设置表达式，同时对表达式进行了解析,setExpression<br>
   *@param1 expression String <br>
   *@return void<br>
   **/
  public void setExpression(String expression){
    StringBuffer expbuff=new StringBuffer();
    expbuff.append("(");
    expbuff.append(expression.trim());
    expbuff.append(")");
    this.exp=new String(expbuff);
    //解析表达式
    parse();
    //具体的数据取模板的数值
    this.number_value=this.number;
  }

  /**
   *计算表达式的值,calc<br>
   *@return result,double<br>
   **/
  public double calc() { //计算表达式
    if(this.exp==null){
      throw new RuntimeException("EXPRESSION_NOT_SET");
    }
    //nstack运算量堆栈
    this.nstack=new java.util.Stack();
    //ostack运算符堆栈
    this.ostack=new java.util.Stack();
    int size=this.all.size();
    //npos存放当前处理到第几个运算量
    int npos=0;
    for(int i=0;i<size;i++){
      //处理元素element
      String element=(String)this.all.get(i);
      //判断元素element是否为运算符
      if(isOperator(element)==this.FLAG_TRUE){ //元素是运算符
        if(element.equals(OPER_LEFT_BRACKET)){ //如果运算符是左括号
          //放入左括号
          ostack.push(element);
        } else if(element.equals(OPER_RIGHT_BRACKET)){ //如果是右括号
          //获取目前ostack运算符堆栈顶层元素topNow
          String topNow=(String)ostack.peek();
          while(!(topNow.equals(OPER_LEFT_BRACKET))){ //如果没有碰到左括号
            //获取目前ostack运算符堆栈顶层元素运算符topOper
            String topOper=(String)ostack.pop();
            //获取目前nstack运算量堆栈顶层元素运算量num2
            String num2=(String)nstack.pop();
            //判断运算符topOper是否为特殊的情况即负号的情况
            if(topOper.equals(OPER_NEG)){ //元素是负号
              //如果运算符topOper是负号,则将运算量num2置为(-num2)放入nstack运算量堆栈
              nstack.push(getNavigate(num2));
            } else{ //元素不是负号
              //再获取目前nstack运算量堆栈顶层元素运算量num1
              String num1=(String)nstack.pop();
              //将目前获取运算量num2,num1,以及运算符topOper进行二则计算
              String result=String.valueOf(calcBaseTwo(num1,topOper,num2));
              //计算结果result再放入nstack运算量堆栈
              nstack.push(result);
            }
            //获取目前ostack运算符堆栈顶层元素topNow
            topNow=(String)ostack.peek();
          }
          //执行到这里表示碰到了左括号
          ostack.pop(); //左括号出栈
        } else{ //其它运算符
          //如果运算符堆栈没有值，那么放入即可
          if((ostack.empty())||
             (((String)ostack.peek()).equals(OPER_LEFT_BRACKET))){
            //放入element到ostack运算符堆栈
            ostack.push(element);
          } else{ //如果是其他情况，需要判断优先级
            //如果入栈运算符优先级高
            if((getPRI(element))>(getPRI((String)ostack.peek()))){
              //放入运算符
              ostack.push(element);
            } else{ //优先级不够，计算前面的金额
              while((getPRI(element))<=(getPRI((String)ostack.peek()))){
                //获取目前ostack运算符堆栈顶层元素运算符topOper
                String topOper=(String)ostack.pop();
                //获取目前nstack运算量堆栈顶层元素运算量num2
                String num2=(String)nstack.pop();
                ////判断运算符topOper是否为特殊的情况即负号的情况
                if(topOper.equals(OPER_NEG)){ //运算符topOper为负号
                  //如果运算符topOper是负号,则将运算量num2置为(-num2)放入nstack运算量堆栈
                  nstack.push(getNavigate(num2));
                } else{ //运算符topOper不是负号
                  //再获取目前nstack运算量堆栈顶层元素运算量num1
                  String num1=(String)nstack.pop();
                  //将目前获取运算量num2,num1,以及运算符topOper进行二则计算
                  String result=String.valueOf(calcBaseTwo(num1,topOper,
                      num2));
                  //计算结果result再放入nstack运算量堆栈
                  nstack.push(result);
                }
              }
              //放入运算符
              ostack.push(element);
            }
          }
        }
      } else{ //是运算量
        //数组中对应数据number_value赋给real_element
    	  String real_element = "";
    	 if(this.number_value!=null&&this.number_value.size()<(npos+1)){
    		 real_element =(String)this.number_value.get(0);
    	 }else{
    		 real_element =(String)this.number_value.get(npos);
    	 }
        //放入real_element到nstack运算量对栈
        nstack.push(real_element);
        //处理到第几个运算量+1
        npos++;
      }
    }
    //将目前nstack运算量堆栈顶层元素运算量数据类型转换成double
    double result=Double.parseDouble((String)(nstack.peek()));
    //获取目前nstack运算量堆栈顶层元素运算量
    nstack.pop();
    if(!nstack.empty()){
      throw new LoanBizException("error expression!");
    }
    nstack=null;
    ostack=null;
    return result;
  }

  /**
   *获得表达式,getExpression<br>
   *@return String(expbuff),String<br>
   **/
  public String getExpression(){ //获得表达式，应该是替换变量后的，可以被优化在上面的方法中实现，条件是上面的方法必须被调用
    //获取表达式运算量,运算符的个数
    int size=this.all.size();
    //存放当前处理到第几个运算量
    int npos=0;
    //存放表达式
    StringBuffer expbuff=new StringBuffer();
    //根据表达式运算量,运算符的个数作循环
    for(int i=0;i<size;i++){
      //获取对应数组中的值
      String element=(String)this.all.get(i);
      //判断是否为运算符
      if(isOperator(element)==this.FLAG_TRUE){
        //最前面和最后面一个括号 去掉
        if(i==0||i==size-1){
          //Do Nothing
        } else{
          //因为负号是用别的符号代替的
          if(element.equals(OPER_NEG)){
            expbuff.append("-");
          } else{
            expbuff.append(element);
          }
        }
      } else{ //是运算量
    	  String real_element = "";
    	  if(this.number_value!=null&&this.number_value.size()<(npos+1)){
    		  real_element = ((String)this.number_value.get(0)).trim();
    	  }else{
    		  real_element=((String)this.number_value.get(npos)).trim();
    	  }
        //将真实的数据放入表达式中
        expbuff.append(real_element);
        npos++;
      }
    }
    //获得变幻后的表达式
    return new String(expbuff);
  }

  /**
   *检验表达式是否合法,isExpValid<br>
   *@return FLAG_TRUE代表合法,FLAG_FALSE代表不合法,String<br>
   **/
  public String isExpValid(){ //检验公式的基本合法性

    //1、加减乘除两边必须要有运算量
    //2、左右括号的数目应该一致
    //3、运算量合法-数字或者$开头
    return this.FLAG_TRUE;
  }

  /**
   *返回模板矢量,getNumeric<br>
   *@return number,ArrayList<br>
   **/
  public List getNumeric(){
    return this.number; //返回模板矢量
  }

  /**
   *对运算量模板设置具体的值,setNewNumeric<br>
   *@param1 new_num ArrayList <br>
   *@return void<br>
   **/
  public void setNewNumeric(List new_num){
    this.number_value=(ArrayList)new_num;
  }


//*************       内部子方法      **************

   /**
    *所有变量重新初始化,initParseResult<br>
    *@return void<br>
    **/
   private void initParseResult(){
     this.number=null;
     this.operator=null;
     this.all=null;
     this.number=new java.util.ArrayList();
     this.operator=new java.util.ArrayList();
     this.all=new java.util.ArrayList();
   }

  /**
   *将表达式进行解析为运算符矢量、运算量矢量、表达式元素矢量,parse<br>
   *@return void<br>
   **/
  private void parse(){
    //在parse之前先将所有变量重新初始化
    initParseResult();
    //获取表达式字符串的长度
    int size=this.exp.length();
    //化成字符数组计算效率应该较高
    char[] ca=new char[size];
    //将表达式字符串化成字符数组
    this.exp.getChars(0,size,ca,0);
    //变量hasValue记录是否在运算符前面记录有运算量的开头位置
    boolean hasValue=false;
    //运算量中的字符位置标识
    int pos=0;
    //根据字符数组个数循环处理
    for(int i=0;i<size;i++){
      //顺序获取字符数组的值
      char t=ca[i];
      //将字符转换成字符串
      String s=String.valueOf(t);
      //判断当前字符是否是操作符
      if(isOperator(s)==this.FLAG_TRUE){ //如果当前字符是操作符
        //如果记录了运算量的位置
        if(hasValue){
          //获得运算量
          String num=new String(ca,pos,i-pos);
          num=num.trim();
          //将运算量放入数组number
          this.number.add(num);
          hasValue=false;
          //将运算量放入数组all
          this.all.add(num);
        } else{ //如果没有记录运算量的位置,表示是开头或者前面一个也是运算符
          //doNothing
        }
        if(i!=0){
          //获取前面的一个字符
          String bs=String.valueOf(ca[i-1]);
          //如果前面的一个字符为"(",接下来的字符为"-",判断是否负号
          if(bs.equals(OPER_LEFT_BRACKET)&&("-".equals(s))){
            s=OPER_NEG;
          }
        }
        //将操作符放入operator
        this.operator.add(s);
        //将操作符放入all
        this.all.add(s);
      } else{
        if(hasValue){
          //doNothing
        } else{
          //如果是空格，放过
          if(t==' '){
          } else{
            //记录运算量中的字符位置
            pos=i;
            //运算量标识置为是
            hasValue=true;
          }
        }
      }
    }
  }


  /**
   *判断是否运算量,isNumeric<br>
   * @param1 s String <br>
   *@return FLAG_TRUE是运算量,FLAG_FALSE不是运算量,String<br>
   **/
   private String isNumeric(String s){ //判断是否运算量
     //如果不是操作符就是运算量
     if((s.equals(OPER_ADD))||(s.equals(OPER_SUB))||(s.equals(OPER_NEG))||
        (s.equals(OPER_DIV))||(s.equals(OPER_MUL))||
        (s.equals(OPER_LEFT_BRACKET))||(s.equals(OPER_RIGHT_BRACKET))){
       return this.FLAG_FALSE;
     } else{
       return this.FLAG_TRUE;
     }

   }

   /**
    *判断是否操作符,isOperator<br>
    * @param1 s String <br>
    *@return FLAG_TRUE是操作符,FLAG_FALSE不是操作符,String<br>
    **/
  private String isOperator(String s){ //判断一个字符串是否是操作符、左括号归为运算符
    if((s.equals(OPER_ADD))||(s.equals(OPER_SUB))||(s.equals(OPER_NEG))||
       (s.equals(OPER_DIV))||(s.equals(OPER_MUL))||
       (s.equals(OPER_LEFT_BRACKET))||(s.equals(OPER_RIGHT_BRACKET))){
      return this.FLAG_TRUE;
    } else{
      return this.FLAG_FALSE;
    }
  }

  /**
   *获得一个操作符的优先级,getPRI<br>
   * @param1 operator String <br>
   *@return 操作符优先级常量,int<br>
   **/
  private int getPRI(String operator){ //获得一个操作符的优先级
    if(operator.equals(OPER_ADD)){
      return PRI_ADD;
    } else if(operator.equals(OPER_SUB)){
      return PRI_SUB;
    } else if(operator.equals(OPER_MUL)){
      return PRI_MUL;
    } else if(operator.equals(OPER_DIV)){
      return PRI_DIV;
    } else if(operator.equals(OPER_RIGHT_BRACKET)){
      return PRI_RIGHT_BRACKET;
    } else if(operator.equals(OPER_NEG)){
      return PRI_NEG;
    } else{
      return PRI_LEFT_BRACKET; //这种情况就是左括号，因为先要判断是否是操作符
    }
  }

  /**
   *根据运算量和运算符进行计算，完成基本二则运算，获得新的运算量,getPRI<br>
   *@param1 num1 String <br>
   *@param2 operator String <br>
   *@param3 num2 String <br>
   *@return 运算结果,double<br>
   **/
  private double calcBaseTwo(String num1,String operator,String num2){
    double i=Double.parseDouble(num1);
    double j=Double.parseDouble(num2);
    if(operator.equals(OPER_ADD)){
      return i+j;
    } else if(operator.equals(OPER_SUB)){
      return i-j;
    } else if(operator.equals(OPER_MUL)){
      return i*j;
    } else if(operator.equals(OPER_DIV)){
      if(j==0){
        return 0;
      }
      return i/j;
    } else{
      throw new RuntimeException("INVALID_OPERATOR");
    }
  }

  /**
   *获取运算量*(-1)后的结果,getPRI<br>
   *@param1 num String <br>
   *@return 运算结果,String<br>
   **/
  private String getNavigate(String num){ //Also can do with String starwith
    double i=calcBaseTwo("-1",OPER_MUL,num);
    return String.valueOf(i);
  }


//*************    测试代码     **********

   public static void main(String[] args){
     try{

       // System.out.println(new java.sql.Timestamp((new java.util.Date()).getTime()));
       ExpressionCalc calc=new ExpressionCalc();
       calc.setExpression("($037-$040+$002+$050)*($040/$038) ");
       // System.out.println("expression is:"+calc.getExpression());
       ArrayList v=new ArrayList();
       v.add("2.00");
       v.add("0.00");
       v.add("29.00");
       v.add("2.00");
       v.add("-20.00");
       v.add("20.00");
       calc.setNewNumeric(v);
       // System.out.println("expression is:"+calc.getExpression());
       calc.calc();
       // System.out.println("expression is:"+calc.calc());
     } catch(Exception e){
       // System.out.println(e);
     }
   }
}
