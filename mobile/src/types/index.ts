export type Employee = {
  _id: string;
  employeeCode: string;
  fullName: string;
  email: string;
  department: string;
  designation: string;
  baseSalary: number;
};

export type Payroll = {
  _id: string;
  employee: Employee;
  month: number;
  year: number;
  basic: number;
  hra: number;
  da: number;
  pf: number;
  tax: number;
  grossSalary: number;
  totalDeductions: number;
  netSalary: number;
};
