package com.coamctech.bxloan.service.approval;

import javax.servlet.http.HttpServletRequest;

import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.freemarker.data.ApprovalCSInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo;
import com.coamctech.bxloan.service.model.SessionUser;

public interface ApprovalFileService {

	ApprovalDKSCInfo getDKSCInfo(ProjectApplication project, SessionUser sessionUser, HttpServletRequest request);

	ApprovalCSInfo getCSInfo(ProjectApplication project, SessionUser sessionUser, HttpServletRequest request);
}
