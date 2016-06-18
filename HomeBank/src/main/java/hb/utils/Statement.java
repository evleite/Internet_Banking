package hb.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import hb.models.Account;
import hb.models.Card;
import hb.models.PDFModel;
import hb.models.Transaction;

public class Statement {

	public static File generateStatement(PDFModel model) throws IOException, DocumentException {
		Account account = model.getAccount();
		Card card = model.getCard();
		List<Transaction> transactions = model.getTransactionList();

		Random rand = new Random();
		String statementName = Long.toString(rand.nextLong()).substring(1) + ".pdf";
		File statementFile = new File(statementName);
		statementFile.createNewFile();

		Document document = new Document(PageSize.A4);

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(statementFile));
		document.open();

		/* Document attributes */
		document.addAuthor("HomeBank");
		document.addCreationDate();
		document.addCreator("HomeBank");
		document.addTitle("Statement");
		document.addSubject("Statement for " + account.getAcc_type().toString() + ": " + account.getIBAN());

		/* Bank logo */
		Image image1 = Image.getInstance("/home/maxim-domentii/Dropbox/!cursuri_calculatoare/PROIECT_LICENTA/Internet_Banking/HomeBank/src/main/webapp/img/home_bank_logo.png");
		image1.setAbsolutePosition(30f, PageSize.A4.getHeight() - 70f);
		image1.scaleAbsolute(185, 50);
		document.add(image1);

		Paragraph emptyP = new Paragraph("                                            ");
		document.add(emptyP);
		document.add(emptyP);
		document.add(emptyP);

		/* Account details */
		PdfPTable table = null;
		if (card != null) {
			table = new PdfPTable(2);
			
			// Set Column widths
			float[] columnWidths = { 1f, 1f };
			table.setWidths(columnWidths);
		} else {
			table = new PdfPTable(1);
		}
		table.setWidthPercentage(100);

		

		PdfPCell header = new PdfPCell(new Paragraph("Account details"));
		header.setColspan(2);
		header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		header.setBorderColor(BaseColor.LIGHT_GRAY);
		header.setPadding(10);
		header.setHorizontalAlignment(Element.ALIGN_LEFT);
		header.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell1 = new PdfPCell(new Paragraph("Account: " + account.getIBAN()));
		cell1.setBorderColor(BaseColor.LIGHT_GRAY);
		cell1.setBorder(Rectangle.TOP | Rectangle.LEFT);
		cell1.setBorderColorBottom(BaseColor.WHITE);
		cell1.setPadding(10);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell2 = new PdfPCell(new Paragraph("Account type: " + account.getAcc_type()));
		cell2.setBorderColor(BaseColor.LIGHT_GRAY);
		cell2.setBorder(Rectangle.LEFT);
		cell2.setPadding(10);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell3 = new PdfPCell(new Paragraph("Currency: " + account.getCurrency()));
		cell3.setBorderColor(BaseColor.LIGHT_GRAY);
		cell3.setBorder(Rectangle.LEFT);
		cell3.setPadding(10);
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell4 = new PdfPCell(new Paragraph("Balance: " + (new DecimalFormat("##.##").format(account.getBalance()))));
		cell4.setBorderColor(BaseColor.LIGHT_GRAY);
		cell4.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
		cell4.setPadding(10);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell5 = null;
		PdfPCell cell6 = null;
		PdfPCell cell7 = null;
		PdfPCell cell8 = null;
		if (card != null) {
			cell5 = new PdfPCell(new Paragraph("Card number: " + 
					card.getCard_no().substring(0, 4) + " " +
					card.getCard_no().substring(4, 8) + " " +
					card.getCard_no().substring(8, 12) + " " +
					card.getCard_no().substring(12)));
			cell5.setBorderColor(BaseColor.LIGHT_GRAY);
			cell5.setBorder(Rectangle.RIGHT | Rectangle.TOP);
			cell5.setPadding(10);
			cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell6 = new PdfPCell(new Paragraph("Card validity: " + card.getValidity()));
			cell6.setBorderColor(BaseColor.LIGHT_GRAY);
			cell6.setBorder(Rectangle.RIGHT);
			cell6.setPadding(10);
			cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell7 = new PdfPCell(new Paragraph("Daily limit: " + card.getDaily_limit()));
			cell7.setBorderColor(BaseColor.LIGHT_GRAY);
			cell7.setBorder(Rectangle.RIGHT);
			cell7.setPadding(10);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell8 = new PdfPCell(new Paragraph(""));
			cell8.setBorderColor(BaseColor.LIGHT_GRAY);
			cell8.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
			cell8.setPadding(10);
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
		}

		if (card != null) {
			table.addCell(header);
			table.addCell(cell1);
			table.addCell(cell5);
			table.addCell(cell2);
			table.addCell(cell6);
			table.addCell(cell3);
			table.addCell(cell7);
			table.addCell(cell4);
			table.addCell(cell8);
		} else {
			table.addCell(header);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
		}

		document.add(table);

		document.add(emptyP);

		/* Transaction grid */
		if (transactions != null && transactions.size() > 0) {
			table = new PdfPTable(4);
			table.setWidthPercentage(100);

			// Set Column widths
			float[] columnWidths2 = { 3f, 5f, 3f, 2f };
			table.setWidths(columnWidths2);

			header = new PdfPCell(new Paragraph("Transaction grid"));
			header.setColspan(4);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderColor(BaseColor.LIGHT_GRAY);
			header.setPadding(10);
			header.setHorizontalAlignment(Element.ALIGN_LEFT);
			header.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell1 = new PdfPCell(new Paragraph("Date"));
			cell1.setBorderColor(BaseColor.LIGHT_GRAY);
			cell1.setBorderColorBottom(BaseColor.WHITE);
			cell1.setPadding(10);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell2 = new PdfPCell(new Paragraph("Transaction details"));
			cell2.setBorderColor(BaseColor.LIGHT_GRAY);
			cell2.setPadding(10);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell3 = new PdfPCell(new Paragraph("Status"));
			cell3.setBorderColor(BaseColor.LIGHT_GRAY);
			cell3.setPadding(10);
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell4 = new PdfPCell(new Paragraph("Amount"));
			cell4.setBorderColor(BaseColor.LIGHT_GRAY);
			cell4.setPadding(10);
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell(header);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);

			for (int i = 0; i < transactions.size(); i++) {
				Transaction transaction = transactions.get(i);
				cell1 = new PdfPCell(new Paragraph(DateUtils.getHumanRedableDate(transaction.getDate())));
				cell1.setBorderColor(BaseColor.LIGHT_GRAY);
				cell1.setBorderColorBottom(BaseColor.WHITE);
				cell1.setPadding(10);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

				cell2 = new PdfPCell(new Paragraph(transaction.getDetails()));
				cell2.setBorderColor(BaseColor.LIGHT_GRAY);
				cell2.setPadding(10);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

				cell3 = new PdfPCell(new Paragraph(transaction.getStatus().toString()));
				cell3.setBorderColor(BaseColor.LIGHT_GRAY);
				cell3.setPadding(10);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

				cell4 = new PdfPCell(new Paragraph("" + new DecimalFormat("##.##").format(transaction.getAmount())));
				cell4.setBorderColor(BaseColor.LIGHT_GRAY);
				cell4.setPadding(10);
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

				if (i == 0) {
					cell1.setBorder(Rectangle.TOP | Rectangle.LEFT);
					cell2.setBorder(Rectangle.TOP);
					cell3.setBorder(Rectangle.TOP);
					cell4.setBorder(Rectangle.TOP | Rectangle.RIGHT);
				} else if (i == transactions.size() - 1) {
					cell1.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
					cell2.setBorder(Rectangle.BOTTOM);
					cell3.setBorder(Rectangle.BOTTOM);
					cell4.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				} else {
					cell1.setBorder(Rectangle.LEFT);
					cell2.setBorder(Rectangle.NO_BORDER);
					cell3.setBorder(Rectangle.NO_BORDER);
					cell4.setBorder(Rectangle.RIGHT);
				}

				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
			}

			document.add(table);
		}

		document.close();
		writer.close();
		
		return statementFile;
	}
}
