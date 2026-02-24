import { authenticateAdmin } from '../services/authService.js';

export const loginAdmin = async (req, res) => {
  const { username, password } = req.body;

  if (!username || !password) {
    return res.status(400).json({ message: 'Username and password are required' });
  }

  const token = await authenticateAdmin({
    username,
    password,
    env: process.env
  });

  if (!token) {
    return res.status(401).json({ message: 'Invalid credentials' });
  }

  return res.json({ token });
};
