import cors from 'cors';
import express from 'express';
import authRoutes from './routes/authRoutes.js';
import employeeRoutes from './routes/employeeRoutes.js';
import payrollRoutes from './routes/payrollRoutes.js';
import { protectAdmin } from './middleware/authMiddleware.js';

export const createApp = () => {
  const app = express();

  app.use(cors());
  app.use(express.json());

  app.get('/health', (_req, res) => res.json({ status: 'ok' }));
  app.use('/api/auth', authRoutes);
  app.use('/api/employees', protectAdmin, employeeRoutes);
  app.use('/api/payrolls', protectAdmin, payrollRoutes);

  return app;
};
