package com.coamctech.bxloan.commons.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfStamperView;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfStamper;

public class PdfStamperView extends AbstractPdfStamperView {

	public static final String DATA = "data";
	public static final String FILENAME = "mergePdfFileName";

	@SuppressWarnings("unchecked")
	@Override
	protected void mergePdfDocument(Map<String, Object> model,
			PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(model.get(FILENAME).toString().getBytes(), "iso8859-1"));
		AcroFields fields = stamper.getAcroFields();
		fillData(fields, (Map<String, String>) model.get(DATA));
		stamper.setFormFlattening(true);
	}

	private void fillData(AcroFields fields, Map<String, String> data)
			throws IOException, DocumentException {
		for (String key : data.keySet()) {
			String value = data.get(key);
			fields.setField(key, value);
		}
	}

}
