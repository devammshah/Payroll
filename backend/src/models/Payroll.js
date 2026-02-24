import mongoose from 'mongoose';

const payrollSchema = new mongoose.Schema(
  {
    employee: { type: mongoose.Schema.Types.ObjectId, ref: 'Employee', required: true },
    month: { type: Number, required: true, min: 1, max: 12 },
    year: { type: Number, required: true },
    basic: { type: Number, required: true },
    hra: { type: Number, required: true },
    da: { type: Number, required: true },
    pf: { type: Number, required: true },
    tax: { type: Number, required: true },
    grossSalary: { type: Number, required: true },
    totalDeductions: { type: Number, required: true },
    netSalary: { type: Number, required: true },
    generatedAt: { type: Date, default: Date.now }
  },
  { timestamps: true }
);

payrollSchema.index({ employee: 1, month: 1, year: 1 }, { unique: true });

export const Payroll = mongoose.model('Payroll', payrollSchema);
