package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FindPrimes {
    Scanner scanner = new Scanner(System.in);

    public FindPrimes(){
       while(scanner.hasNext()){
           System.out.println(biggestPrime(scanner.nextLong()));
       }
    }

    public double biggestPrime(long n){
        for(long i = n/2; i>1; i--){
            if(n%i==0){
                if(isPrime(i)) {
                    return i;
                }

                else{
                   return biggestPrime(i);
                }
            }
        }

        return n;
    }

    public boolean isPrime(long n){
        if(n%2==0)
            return false;
        for(long i = 3; 2*i<=n; i++){
            if(n%i==0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        new FindPrimes();
    }
}
