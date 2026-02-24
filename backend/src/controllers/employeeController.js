import { Employee } from '../models/Employee.js';

export const createEmployee = async (req, res) => {
  try {
    const employee = await Employee.create(req.body);
    return res.status(201).json(employee);
  } catch (error) {
    return res.status(400).json({ message: error.message });
  }
};

export const getEmployees = async (_req, res) => {
  const employees = await Employee.find().sort({ createdAt: -1 });
  return res.json(employees);
};

export const updateEmployee = async (req, res) => {
  try {
    const employee = await Employee.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
      runValidators: true
    });

    if (!employee) {
      return res.status(404).json({ message: 'Employee not found' });
    }

    return res.json(employee);
  } catch (error) {
    return res.status(400).json({ message: error.message });
  }
};

export const deleteEmployee = async (req, res) => {
  const employee = await Employee.findByIdAndDelete(req.params.id);

  if (!employee) {
    return res.status(404).json({ message: 'Employee not found' });
  }

  return res.status(204).send();
};
