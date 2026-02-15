package com.payroll.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import androidx.core.content.FileProvider
import com.payroll.data.entity.EmployeeEntity
import com.payroll.data.entity.PayrollEntity
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PdfGenerator {

    fun generatePayslip(
        context: Context,
        employee: EmployeeEntity,
        payroll: PayrollEntity,
        calc: SalaryCalculator.Result
    ): File {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val titlePaint = Paint().apply { textSize = 20f; isFakeBoldText = true }
        val textPaint = Paint().apply { textSize = 12f }

        var y = 60
        canvas.drawText("Acme Payroll Services", 40f, y.toFloat(), titlePaint)
        y += 30
        canvas.drawText("Salary Slip - ${payroll.month}/${payroll.year}", 40f, y.toFloat(), textPaint)
        y += 25
        canvas.drawText("Employee: ${employee.fullName} (ID: ${employee.employeeId})", 40f, y.toFloat(), textPaint)
        y += 20
        canvas.drawText("Department: ${employee.department} | Designation: ${employee.designation}", 40f, y.toFloat(), textPaint)
        y += 35

        val rows = listOf(
            "Basic Salary" to employee.basicSalary,
            "HRA" to calc.hra,
            "DA" to calc.da,
            "Allowances" to employee.otherAllowances,
            "Gross Salary" to calc.gross,
            "PF" to calc.pf,
            "Tax" to calc.tax,
            "Total Deductions" to calc.deductions,
            "Net Salary" to calc.net
        )

        rows.forEach {
            canvas.drawText(it.first, 40f, y.toFloat(), textPaint)
            canvas.drawText("₹ ${"%.2f".format(it.second)}", 360f, y.toFloat(), textPaint)
            y += 20
        }

        y += 30
        canvas.drawText("Authorized Signature: ______________________", 40f, y.toFloat(), textPaint)

        document.finishPage(page)

        val docFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: context.filesDir
        val file = File(docFolder, "payslip_${employee.employeeId}_${payroll.month}_${payroll.year}.pdf")
        FileOutputStream(file).use { document.writeTo(it) }
        document.close()
        return file
    }

    fun getUriForFile(context: Context, file: File) =
        FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

    fun nowFormatted(): String = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
}
