const round = (value) => Number(value.toFixed(2));

export const calculateSalary = (basic, rates = {}) => {
  const {
    hraRate = 0.2,
    daRate = 0.1,
    pfRate = 0.12,
    taxRate = 0.1
  } = rates;

  const hra = round(basic * hraRate);
  const da = round(basic * daRate);
  const grossSalary = round(basic + hra + da);
  const pf = round(basic * pfRate);
  const tax = round(grossSalary * taxRate);
  const totalDeductions = round(pf + tax);
  const netSalary = round(grossSalary - totalDeductions);

  return { basic, hra, da, pf, tax, grossSalary, totalDeductions, netSalary };
};
