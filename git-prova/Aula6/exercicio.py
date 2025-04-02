import math
A = int(input("Digite o primeiro valor inteiro: "))
B = int(input("Digite o segundo valor inteiro: "))
C = int(input("Digite o terceiro valor inteiro: "))

delta = B**2 - 4*A*C

if A != 0:
    if delta >= 0:
        raiz1 = (-B + math.sqrt(delta)) / (2 * A)
        raiz2 = (-B - math.sqrt(delta)) / (2 * A)
        print(f"As raízes reais são: {raiz1: 0.2F} e {raiz2: 0.2F}")
    else:
        print("nao há raizes reais")

elif B != 0:
    x = -C/B
    print(F"A raiz é {x: .2F}")

else:
    print("ENTRADA INVALIDA")