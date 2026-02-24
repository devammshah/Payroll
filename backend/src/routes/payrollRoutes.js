import { Router } from 'express';
import {
  generateMonthlyPayroll,
  getPayrollById,
  listPayrolls
} from '../controllers/payrollController.js';

const router = Router();

router.get('/', listPayrolls);
router.get('/:id', getPayrollById);
router.post('/generate', generateMonthlyPayroll);

export default router;
