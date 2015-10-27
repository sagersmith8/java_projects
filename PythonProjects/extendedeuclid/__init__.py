from random import randint

def extendedEuclid(x,y):
    a = 0
    b = 0
   
    if x>y:
        a = x
        b = y
    else:
        a = y
        b = x
    s = 1
    t = 0
    u = 0
    v = 1
    
    while b!=0:
         r = a % b
         q = a//b
         a = b
         b = r
         uu = s-u*q
         vv = t-v*q
         s = u
         t = v
         u = uu
         v = vv
         
    gcd = a
    
    print("The GCD of",x,"and",y,"is",gcd,".")
    print("s("+str(s)+")*A("+str(x)+") +"+"t("+str(t)+")*B("+str(y)+") = gcd("+"A("+str(x)+"),B("+str(y)+")) ="+str(gcd));
    print("")

for i in range(20):
    extendedEuclid(randint(1,10000), randint(1,10000))
    