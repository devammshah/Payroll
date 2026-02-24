# Premium Employee Payroll & Salary Slip Generator

A full-stack fintech-style solution with:
- **Mobile app**: React Native + TypeScript
- **Backend API**: Node.js + Express + MongoDB

## Highlights
- Secure **JWT admin authentication**
- Employee management (**CRUD**)
- Automated salary engine with **HRA, DA, PF, Tax, Net Salary**
- Monthly payroll generation with duplicate prevention (`employee + month + year` unique index)
- Professional PDF payslip generation + share support
- Blue/teal tech-vibe dashboard cards, animations, and dark mode-first styling

---

## Backend (`/backend`)
### Features
- Admin login from env credentials
- Protected employee and payroll APIs
- Mongoose models for employees + payrolls
- Payroll generation endpoint with conflict handling for duplicates

### Quick start
```bash
cd backend
cp .env.example .env
npm install
npm run dev
```

### API overview
- `POST /api/auth/login`
- `GET|POST /api/employees`
- `PUT|DELETE /api/employees/:id`
- `GET /api/payrolls`
- `GET /api/payrolls/:id`
- `POST /api/payrolls/generate`

---

## Mobile (`/mobile`)
### Features
- Admin auth flow with token persistence
- Dashboard analytics cards
- Employees and Payroll screens
- PDF payslip generation + preview/download/share workflow support

### Quick start
```bash
cd mobile
npm install
npm run android
```

Set backend base URL in `src/api/client.ts` if running on physical device/emulator.

---

## Salary Formula
- `HRA = basic * hraRate (default 20%)`
- `DA = basic * daRate (default 10%)`
- `Gross = basic + HRA + DA`
- `PF = basic * pfRate (default 12%)`
- `Tax = Gross * taxRate (default 10%)`
- `Net Salary = Gross - (PF + Tax)`

All calculations are rounded to 2 decimals.
