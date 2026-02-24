import mongoose from 'mongoose';

const employeeSchema = new mongoose.Schema(
  {
    employeeCode: { type: String, required: true, unique: true, trim: true },
    fullName: { type: String, required: true, trim: true },
    email: { type: String, required: true, unique: true, lowercase: true, trim: true },
    department: { type: String, required: true, trim: true },
    designation: { type: String, required: true, trim: true },
    joiningDate: { type: Date, required: true },
    bankAccount: { type: String, trim: true },
    panNumber: { type: String, trim: true },
    baseSalary: { type: Number, required: true, min: 0 }
  },
  { timestamps: true }
);

export const Employee = mongoose.model('Employee', employeeSchema);
