# Employee Payroll & Salary Slip Generator (Android, Kotlin)

A production-style Android app using **Kotlin + MVVM + Room + Material 3 + ViewBinding + Hilt**.

## Features
- Admin login with encrypted session storage
- Employee CRUD with search
- Automated salary calculation (gross, deductions, net)
- Monthly payroll generation with duplicate prevention per employee/month/year
- PDF payslip generation and sharing
- Payroll reporting with filters (month, year, department) + payout summary

## Project Structure
```
app/src/main/java/com/payroll/
  data/
    dao/
    database/
    entity/
  repository/
  viewmodel/
  utils/
  ui/
    login/
    dashboard/
    employee/
    payroll/
  di/
```

## Setup Instructions
1. Open the project in **Android Studio Hedgehog+**.
2. Let Gradle sync and install SDK 34 / build tools.
3. Build and run on emulator/device (min SDK 24).
4. Login using:
   - Username: `admin`
   - Password: `admin@123`
5. Add employees, generate payroll, then share generated payslip PDFs.

## Notes
- PDFs are saved in app-specific Documents directory.
- Session uses `EncryptedSharedPreferences`.
- Room enforces unique monthly payroll per employee via database index.
