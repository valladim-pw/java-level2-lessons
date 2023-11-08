package Less02_Lambda_Recursion._3_Recursion;

public class _3OptimizedPowerSteps {
	public static double power(double val, int pow) {
		System.out.println("Прямой ход, power = " + pow);
		double result = 0;
		switch(pow) {
			case 0 : result = 1; break;
			case 1 : result = val; break;
			default : {
				double val_halfPower = power(val, pow / 2);
				if(pow % 2 == 1)
					result = val * val_halfPower * val_halfPower;
				else
					result = val_halfPower * val_halfPower;
			}
		}
		System.out.println("Обратный ход, power = " + pow + ", result = " + result);
		return result;
	}
	
	public static void main(String[] args) {
		power(2,1000);
	}
}
