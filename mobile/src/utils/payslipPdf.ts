import { PDFDocument, rgb, StandardFonts } from 'react-native-pdf-lib';
import Share from 'react-native-share';
import RNFS from 'react-native-fs';
import { Payroll } from '../types';

export const generateAndSharePayslip = async (payroll: Payroll) => {
  const pageWidth = 595;
  const pageHeight = 842;
  const docsPath = RNFS.DocumentDirectoryPath;
  const filePath = `${docsPath}/Payslip_${payroll.employee.employeeCode}_${payroll.month}_${payroll.year}.pdf`;

  const pdf = PDFDocument.create(filePath)
    .addPages(
      PDFDocument.Page.create()
        .setMediaBox(pageWidth, pageHeight)
        .drawText('Premium Salary Slip', {
          x: 50,
          y: 790,
          color: rgb(0.04, 0.52, 0.73),
          fontSize: 24,
          fontName: StandardFonts.HelveticaBold
        })
        .drawText(`Employee: ${payroll.employee.fullName}`, { x: 50, y: 750 })
        .drawText(`Department: ${payroll.employee.department}`, { x: 50, y: 730 })
        .drawText(`Period: ${payroll.month}/${payroll.year}`, { x: 50, y: 710 })
        .drawText(`Basic: ₹${payroll.basic}`, { x: 50, y: 660 })
        .drawText(`HRA: ₹${payroll.hra}`, { x: 50, y: 640 })
        .drawText(`DA: ₹${payroll.da}`, { x: 50, y: 620 })
        .drawText(`PF: ₹${payroll.pf}`, { x: 50, y: 600 })
        .drawText(`Tax: ₹${payroll.tax}`, { x: 50, y: 580 })
        .drawText(`Net Salary: ₹${payroll.netSalary}`, {
          x: 50,
          y: 540,
          fontName: StandardFonts.HelveticaBold,
          color: rgb(0.08, 0.72, 0.65)
        })
    );

  await pdf.write();

  await Share.open({
    title: 'Share Payslip',
    url: `file://${filePath}`
  });

  return filePath;
};
