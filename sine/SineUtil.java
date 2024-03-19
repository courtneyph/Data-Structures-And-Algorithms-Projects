/**
 * Provides implementations of Sine Approximation Algorithms
 * and auxiliary methods
 * @author William Duncan, Courtney Pham
 * <pre>
 * Date: September 1, 2023
 * Course: csc 3102
 * Project # 0
 * Instructor: Dr. Duncan
 * </pre>
 */

public class SineUtil {
    /**
     * Computes the factorial of the specified number
     * @param n the number whose factorial is to be determined
     * @return n!
     * @throw IllegalArgumentException when n < 0 
     */
    private static double factorial(int n)
    {
        
        if (n < 0) {
            throw new IllegalArgumentException("Unable to determine negative factorials.");

        }
        double factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
        }

    
    /**
     * Computes the specified power
     * @param x the base of the power
     * @param n the exponent of the power
     * @return x^n
     * @throw IllegalArgumentException when x = 0 and n <= 0 
     */
    private static double pow(double x, int n)
    {
        double result = 1;
        if (x==0 && n<=0) {
            throw new IllegalArgumentException("Unable to complete this operation. Please try again. x cannot equal 0 and n has to be greater than 0.");
        }
        else if (n == 0 || x == 1) {
            result = 1;
        }
        else if (n % 2 == 0 && x == -1) {
            result = 1;
        }
        else if (n % 2 == 1 && x ==-1) {
            result = -1;
        }   
        else if (n > 0 && x == 0) {
            result = 0;
        }
        else if (n > 0) {
            for (int i = 1; i <= n; i++) {
                result *= x;
            }
        }
        else if (n < 0) {
            for (int i = -1; i >= n; i++) {
                result *= x;
            }
        }
        return result;
        }    
    
    /**
     * Computes the sine of an angle using the Taylor Series approximation of the
     * sine function and naive exponentiation
     * @param x angle in radians
     * @param n number of terms
     * @return sine(x) = x - x^3/3! + x^5/5! - x^7/7! .....
     * @throw IllegalArgumentException when n <= 0
     */
    public static double naiveSine(double x, int n) {
        double sine = 0;

        if(n <= 0) {
        throw new IllegalArgumentException("n must be an integer greater than 0.");
        }
        else {
            sine = 0;
        }
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                sine = sine - pow(x,(2*i)-1)/ factorial((2*i)-1);
            }
            else {
                sine = sine + pow(x,(2*i)-1) / factorial((2*i)-1);
            }

        }
        return sine;
    }    
    
    /**
     * Computes the sine of an angle using the Taylor Series approximation of the
     * sine function and fast exponentiation
     * @param x angle in radians
     * @param n number of terms
     * @return sine(x) = x - x^3/3! + x^5/5! - x^7/7! .....
     * @throw IllegalArgumentException when n <= 0
     */
    public static double fastSine(double x, int n)
    {
        if(n<=0){
        throw new IllegalArgumentException("n must be greater than 0!");
    }
    else {
        double fastSine = x;
        int denom = 3;
        int factor = -1;
        double base = x;
    for (int i = 2; i <= n; i++) {
        base = base * (x / denom) * (x / (denom - 1));
        denom = denom + 2;
        fastSine = fastSine + (factor * base);
        factor = factor * -1;
    }
        return fastSine;
    }
    }}