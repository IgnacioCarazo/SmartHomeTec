using BackEndModel;
using iTextSharp.text;
using iTextSharp.text.pdf;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.DataManagement
{
    /// <summary>
    /// clase para genenerar pdf de factura y garantia
    /// </summary>
    public class InvoiceReport
    {
        #region Declaration
        int _totalColum = 3;
        Document _document;
        Font _fontStyle;
        PdfPTable _pdfTable = new PdfPTable(2);
        PdfPCell _pdfPCell;
        MemoryStream _memoryStream = new MemoryStream();

        Document _documentw;
        Font _fontStylew;
        PdfPTable _pdfTablew = new PdfPTable(2);
        PdfPCell _pdfPCellw;
        MemoryStream _memoryStreamw = new MemoryStream();

        Invoice _invoice = new Invoice();
        Warranty _deviceWaranty = new Warranty();
        #endregion

        /// <summary>
        /// prepara el pdf
        /// </summary>
        /// <param name="invoice">invoice a enviar</param>
        /// <returns>array de bite</returns>
        public byte[] PrepareReport(Invoice invoice)
        {
            _invoice = invoice;

            #region
            _document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
            _document.SetPageSize(PageSize.A4);
            _document.SetMargins(20f, 20f, 20f, 20f);
            _pdfTable.WidthPercentage = 100;
            _pdfTable.HorizontalAlignment = Element.ALIGN_LEFT;
            _fontStyle = FontFactory.GetFont("Tahoma", 11f, 1);
            PdfWriter.GetInstance(_document, _memoryStream);
            _document.Open();
            #endregion

            this.ReportHeader();
            this.ReportBody();
            _pdfTable.HeaderRows = 2;
            _document.Add(_pdfTable);
            _document.Close();

            return _memoryStream.ToArray();
        }

        /// <summary>
        /// metodo para estableces headers del documento
        /// </summary>
        private void ReportHeader()
        {
            _fontStyle = FontFactory.GetFont("Tahoma", 22f, 1);
            _pdfPCell = new PdfPCell(new Phrase("SmartHome TEC", _fontStyle));
            _pdfPCell.Colspan = _totalColum;
            _pdfPCell.HorizontalAlignment = Element.ALIGN_CENTER;
            _pdfPCell.Border = 0;
            _pdfPCell.BackgroundColor = BaseColor.WHITE;
            _pdfPCell.ExtraParagraphSpace = 0;
            _pdfTable.AddCell(_pdfPCell);
            _pdfTable.CompleteRow();


            _fontStyle = FontFactory.GetFont("Tahoma", 20f, 1);
            _pdfPCell = new PdfPCell(new Phrase("Client Invoice", _fontStyle));
            _pdfPCell.Colspan = _totalColum;
            _pdfPCell.HorizontalAlignment = Element.ALIGN_CENTER;
            _pdfPCell.Border = 0;
            _pdfPCell.BackgroundColor = BaseColor.WHITE;
            _pdfPCell.ExtraParagraphSpace = 0;
            _pdfTable.AddCell(_pdfPCell);
            _pdfTable.CompleteRow();
        }

        /// <summary>
        /// cuerpo del pdf de invoice
        /// </summary>
        private void ReportBody()
        {
            _fontStyle = FontFactory.GetFont("Tahoma", 18f, 1);
            _pdfPCell = new PdfPCell(InvoiceHeaders());
            _pdfPCell.Colspan = 2;
            _pdfPCell.Border = 0;
            _pdfTable.AddCell(_pdfPCell);
            _pdfTable.CompleteRow();
        }

        /// <summary>
        /// define headers del pdf invoice
        /// </summary>
        /// <returns></returns>
        private PdfPTable InvoiceHeaders()
        {
            PdfPTable oPdfPTable = new PdfPTable(2);
            oPdfPTable.SetWidths(new float[] { 100f, 100f });
            oPdfPTable.SpacingBefore = 50f;
            

            _pdfPCell = new PdfPCell(new Phrase("Invoice Number: ", _fontStyle));
            _pdfPCell.Border = 2;
            oPdfPTable.AddCell(_pdfPCell);

            _pdfPCell = new PdfPCell(new Phrase(_invoice.invoiceNumber.ToString(), _fontStyle));
            _pdfPCell.Border = 0;
            oPdfPTable.AddCell(_pdfPCell);
            oPdfPTable.CompleteRow();

            _pdfPCell = new PdfPCell(new Phrase("Device Type: ", _fontStyle));
            _pdfPCell.Border = 2;
            oPdfPTable.AddCell(_pdfPCell);

            _pdfPCell = new PdfPCell(new Phrase(_invoice.deviceTypeName, _fontStyle));
            _pdfPCell.Border = 0;
            oPdfPTable.AddCell(_pdfPCell);
            oPdfPTable.CompleteRow();

            _pdfPCell = new PdfPCell(new Phrase("Total Amount: ", _fontStyle));
            _pdfPCell.Border = 2;
            oPdfPTable.AddCell(_pdfPCell);

            _pdfPCell = new PdfPCell(new Phrase("$"+_invoice.price.ToString(), _fontStyle));
            _pdfPCell.Border = 0;
            oPdfPTable.AddCell(_pdfPCell);
            oPdfPTable.CompleteRow();

            _pdfPCell = new PdfPCell(new Phrase("Date Purchased: ", _fontStyle));
            _pdfPCell.Border = 2;
            oPdfPTable.AddCell(_pdfPCell);

            _pdfPCell = new PdfPCell(new Phrase(_invoice.date, _fontStyle));
            _pdfPCell.Border = 0;
            oPdfPTable.AddCell(_pdfPCell);
            oPdfPTable.CompleteRow();

            return oPdfPTable;
        }

        /// <summary>
        /// genera el pdf de warranty
        /// </summary>
        /// <param name="warranty">garantia a enviar</param>
        /// <returns>array byte con informacion del pdf</returns>
        public byte[] PrepareReportW(Warranty warranty)
        {
            _deviceWaranty = warranty;

            #region
            _documentw = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
            _documentw.SetPageSize(PageSize.A4);
            _documentw.SetMargins(20f, 20f, 20f, 20f);
            _pdfTablew.WidthPercentage = 100;
            _pdfTablew.HorizontalAlignment = Element.ALIGN_LEFT;
            _fontStylew = FontFactory.GetFont("Times New Roman", 11f, 1);
            PdfWriter.GetInstance(_documentw, _memoryStreamw);
            _documentw.Open();
            #endregion

            this.ReportHeaderW();
            this.ReportBodyW();
            _pdfTablew.HeaderRows = 2;
            _documentw.Add(_pdfTablew);
            _documentw.Close();

            return _memoryStreamw.ToArray();
        }

        /// <summary>
        /// header del documento
        /// </summary>
        private void ReportHeaderW()
        {
            _fontStylew = FontFactory.GetFont("Times New Roman", 22f, 1);
            _pdfPCellw = new PdfPCell(new Phrase("SmartHome TEC", _fontStylew));
            _pdfPCellw.Colspan = _totalColum;
            _pdfPCellw.HorizontalAlignment = Element.ALIGN_CENTER;
            _pdfPCellw.Border = 0;
            _pdfPCellw.BackgroundColor = BaseColor.WHITE;
            _pdfPCellw.ExtraParagraphSpace = 0;
            _pdfTablew.AddCell(_pdfPCellw);
            _pdfTablew.CompleteRow();


            _fontStylew = FontFactory.GetFont("Times New Roman", 20f, 1);
            _pdfPCellw = new PdfPCell(new Phrase("Device Warranty", _fontStylew));
            _pdfPCellw.Colspan = _totalColum;
            _pdfPCellw.HorizontalAlignment = Element.ALIGN_CENTER;
            _pdfPCellw.Border = 0;
            _pdfPCellw.BackgroundColor = BaseColor.WHITE;
            _pdfPCellw.ExtraParagraphSpace = 0;
            _pdfTablew.AddCell(_pdfPCellw);
            _pdfTablew.CompleteRow();
        }

        /// <summary>
        /// formato del documento
        /// </summary>
        private void ReportBodyW()
        {
            _fontStylew = FontFactory.GetFont("Times New Roman", 18f, 1);
            _pdfPCellw = new PdfPCell(WarrantyHeaders());
            _pdfPCellw.Colspan = 2;
            _pdfPCellw.Border = 0;
            _pdfTablew.AddCell(_pdfPCellw);
            _pdfTablew.CompleteRow();
        }

        /// <summary>
        /// headers del pdf warranty
        /// </summary>
        /// <returns></returns>
        private PdfPTable WarrantyHeaders()
        {
            PdfPTable oPdfPTablew = new PdfPTable(2);
            oPdfPTablew.SetWidths(new float[] { 100f, 100f });
            oPdfPTablew.SpacingBefore = 50f;


            _pdfPCellw = new PdfPCell(new Phrase("Client Name: ", _fontStylew));
            _pdfPCellw.Border = 2;
            oPdfPTablew.AddCell(_pdfPCellw);

            _pdfPCellw = new PdfPCell(new Phrase(_deviceWaranty.clientName, _fontStylew));
            _pdfPCellw.Border = 0;
            oPdfPTablew.AddCell(_pdfPCellw);
            oPdfPTablew.CompleteRow();

            _pdfPCellw = new PdfPCell(new Phrase("Device Serial Number: ", _fontStylew));
            _pdfPCellw.Border = 2;
            oPdfPTablew.AddCell(_pdfPCellw);

            _pdfPCellw = new PdfPCell(new Phrase(_deviceWaranty.deviceSerialNumber.ToString(), _fontStylew));
            _pdfPCellw.Border = 0;
            oPdfPTablew.AddCell(_pdfPCellw);
            oPdfPTablew.CompleteRow();

            _pdfPCellw = new PdfPCell(new Phrase("Device Brand: ", _fontStylew));
            _pdfPCellw.Border = 2;
            oPdfPTablew.AddCell(_pdfPCellw);

            _pdfPCellw = new PdfPCell(new Phrase(_deviceWaranty.brand, _fontStylew));
            _pdfPCellw.Border = 0;
            oPdfPTablew.AddCell(_pdfPCellw);
            oPdfPTablew.CompleteRow();

            _pdfPCellw = new PdfPCell(new Phrase("Device Type: ", _fontStylew));
            _pdfPCellw.Border = 2;
            oPdfPTablew.AddCell(_pdfPCellw);

            _pdfPCellw = new PdfPCell(new Phrase(_deviceWaranty.deviceTypeName, _fontStylew));
            _pdfPCellw.Border = 0;
            oPdfPTablew.AddCell(_pdfPCellw);
            oPdfPTablew.CompleteRow();

            _pdfPCellw = new PdfPCell(new Phrase("Purchase Date: ", _fontStylew));
            _pdfPCellw.Border = 2;
            oPdfPTablew.AddCell(_pdfPCellw);

            _pdfPCellw = new PdfPCell(new Phrase(_deviceWaranty.purchaseDate, _fontStylew));
            _pdfPCellw.Border = 0;
            oPdfPTablew.AddCell(_pdfPCellw);
            oPdfPTablew.CompleteRow();

            _pdfPCellw = new PdfPCell(new Phrase("Warranty Expire Date: ", _fontStylew));
            _pdfPCellw.Border = 2;
            oPdfPTablew.AddCell(_pdfPCellw);

            _pdfPCellw = new PdfPCell(new Phrase(_deviceWaranty.expireDate, _fontStylew));
            _pdfPCellw.Border = 0;
            oPdfPTablew.AddCell(_pdfPCellw);
            oPdfPTablew.CompleteRow();

            return oPdfPTablew;
        }

        /// <summary>
        /// metodo para generar objeto invoice a enviar
        /// </summary>
        /// <param name="order">order de la cual se genera invoice</param>
        /// <param name="typeName">tipo de device en invoice</param>
        /// <returns>invoice</returns>
        public Invoice generateInvoice(Order order, string typeName)
        {
            Invoice newInvoice = new Invoice();
            newInvoice.invoiceNumber = order.orderID;
            newInvoice.deviceTypeName = typeName;
            newInvoice.price = order.price;
            newInvoice.date = order.date;
            return newInvoice;
        }

        /// <summary>
        /// metodo para generar warranty apartir de una order
        /// </summary>
        /// <param name="order">order</param>
        /// <param name="ownerName">nombre del comprador</param>
        /// <param name="device">device de la garantia</param>
        /// <param name="warranty">garantia</param>
        /// <returns>warranty</returns>
        public Warranty generateWarranty(Order order, string ownerName, Device device, int warranty)
        {
            Warranty newWarranty = new Warranty();
            newWarranty.clientName = ownerName;
            newWarranty.deviceSerialNumber = order.deviceSerialNumber;
            newWarranty.brand = device.brand;
            newWarranty.deviceTypeName = device.typeName;
            newWarranty.purchaseDate = order.date;
            DateTime orderDate = DateTime.ParseExact(order.date,"dd/MM/yyyy",null);
            DateTime warrantyTime = orderDate.AddMonths(warranty);
            newWarranty.expireDate = warrantyTime.ToString("dd/MM/yyyy");
            return newWarranty;
        }

        /// <summary>
        /// metodo para enviar por email los pdf invoice y warranty
        /// </summary>
        /// <param name="invoice">invoice a enviar</param>
        /// <param name="deviceOwner">email del client</param>
        /// <param name="warranty">warranty a enviar</param>
        public void sendPDF(Invoice invoice, string deviceOwner, Warranty warranty)
        {
            byte[] ibytes = this.PrepareReport(invoice);
            byte[] wbytes = this.PrepareReportW(warranty);

            string from = "mariohaziel@hotmail.com";
            string to = deviceOwner;
            MailMessage mm = new MailMessage(from, to);
            mm.Subject = "SmartHome Invoice And Warranty";
            mm.Body = "Purchase PDF Attachment";
            mm.Attachments.Add(new Attachment(new MemoryStream(ibytes), "Invoice.pdf"));
            mm.Attachments.Add(new Attachment(new MemoryStream(wbytes), "Warranty.pdf"));
            mm.IsBodyHtml = true;
            SmtpClient smtp = new SmtpClient();
            smtp.Host = "smtp.live.com";
            smtp.EnableSsl = true;
            NetworkCredential NetworkCred = new NetworkCredential();
            NetworkCred.UserName = "mariohaziel@hotmail.com";
            NetworkCred.Password = "HAGURO112799";
            smtp.Credentials = NetworkCred;
            smtp.Port = 587;
            smtp.Send(mm);
        }
    }


}
