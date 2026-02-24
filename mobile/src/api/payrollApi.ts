import api from './client';
import { Employee, Payroll } from '../types';

export const login = (username: string, password: string) =>
  api.post<{ token: string }>('/auth/login', { username, password });

export const fetchEmployees = () => api.get<Employee[]>('/employees');

export const upsertEmployee = (employee: Partial<Employee> & { _id?: string }) =>
  employee._id
    ? api.put<Employee>(`/employees/${employee._id}`, employee)
    : api.post<Employee>('/employees', employee);

export const removeEmployee = (id: string) => api.delete(`/employees/${id}`);

export const fetchPayrolls = () => api.get<Payroll[]>('/payrolls');

export const generatePayroll = (employeeId: string, month: number, year: number) =>
  api.post<Payroll>('/payrolls/generate', { employeeId, month, year });
