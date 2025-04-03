numero = int(input("Digite um número inteiro positivo:"))

if numero <= 1:
    print("O número deve ser maior que 1 para ser um número primo.")
else:
    for i in range(2, numero):
        if numero % i == 0:
            print(f"O número {numero} não é primo.")
            break
    else:
        print(f"O número {numero} é primo.")