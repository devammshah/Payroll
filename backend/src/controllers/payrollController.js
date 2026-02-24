import { Employee } from '../models/Employee.js';
import { Payroll } from '../models/Payroll.js';
import { calculateSalary } from '../utils/salary.js';

export const generateMonthlyPayroll = async (req, res) => {
  const { employeeId, month, year, rates } = req.body;

  const employee = await Employee.findById(employeeId);
  if (!employee) {
    return res.status(404).json({ message: 'Employee not found' });
  }

  const salaryBreakdown = calculateSalary(employee.baseSalary, rates);

  try {
    const payroll = await Payroll.create({
      employee: employee._id,
      month,
      year,
      ...salaryBreakdown
    });

    return res.status(201).json(payroll);
  } catch (error) {
    if (error.code === 11000) {
      return res.status(409).json({
        message: 'Payroll already generated for this employee and month'
      });
    }

    return res.status(400).json({ message: error.message });
  }
};

export const listPayrolls = async (req, res) => {
  const { month, year } = req.query;
  const query = {};

  if (month) query.month = Number(month);
  if (year) query.year = Number(year);

  const payrolls = await Payroll.find(query)
    .populate('employee')
    .sort({ year: -1, month: -1, createdAt: -1 });

  return res.json(payrolls);
};

export const getPayrollById = async (req, res) => {
  const payroll = await Payroll.findById(req.params.id).populate('employee');

  if (!payroll) {
    return res.status(404).json({ message: 'Payroll not found' });
  }

  return res.json(payroll);
};
