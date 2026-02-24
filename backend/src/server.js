import dotenv from 'dotenv';
import { connectDB } from './config/db.js';
import { createApp } from './app.js';
import { initializeAdminCredentials } from './services/authService.js';

dotenv.config();

const required = ['PORT', 'MONGO_URI', 'JWT_SECRET', 'ADMIN_USERNAME', 'ADMIN_PASSWORD'];
required.forEach((key) => {
  if (!process.env[key]) {
    throw new Error(`Missing env: ${key}`);
  }
});

await initializeAdminCredentials(process.env.ADMIN_PASSWORD);
await connectDB(process.env.MONGO_URI);

const app = createApp();
app.listen(process.env.PORT, () => {
  console.log(`Payroll backend running on port ${process.env.PORT}`);
});
