import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';

let adminHash;

export const initializeAdminCredentials = async (adminPassword) => {
  adminHash = await bcrypt.hash(adminPassword, 10);
};

export const authenticateAdmin = async ({ username, password, env }) => {
  if (username !== env.ADMIN_USERNAME) {
    return null;
  }

  const validPassword = await bcrypt.compare(password, adminHash);
  if (!validPassword) {
    return null;
  }

  return jwt.sign({ role: 'admin', username }, env.JWT_SECRET, {
    expiresIn: env.JWT_EXPIRES_IN || '1d'
  });
};
