# Exercício 01: Elaborar um fluxograma que
# permita a leitura de dois números inteiros e exiba o maior deles. 
# Use estrutura Se-Então.

# Leitura dos dois números inteiros
a = int(input("Digite o primeiro número (a): "))
b = int(input("Digite o segundo número (b): "))

# Verificação de qual número é o maior
if a > b:
    print(f"O maior número é: {a}")
else:
    print(f"O maior número é: {b}")


# Exercício 02: Elaborar um fluxograma que permita a leitura de dois números
# inteiros e exiba a soma deles apenas se o segundo número for um múltiplo de 3. 

# Leitura dos dois números inteiros
a = int(input("Digite o primeiro número (a): "))
b = int(input("Digite o segundo número (b): "))

# Verificação se o segundo número é múltiplo de 3
if b % 3 == 0:
    soma = a + b
    print(f"A soma dos números é: {soma}")
else:
    print("O segundo número não é múltiplo de 3. Não será realizada a soma.")


# Exercício 03: Elaborar um fluxograma que permita a leitura de dois números inteiros e 
# exiba o maior deles. Use estrutura Se-Então-Senão.
# Leitura dos dois números inteiros
a = int(input("Digite o primeiro número (a): "))
b = int(input("Digite o segundo número (b): "))

# Verificação de qual número é o maior
if a > b:
    print(f"O maior número é: {a}")
else:
    print(f"O maior número é: {b}")


# Exercício 05: Elaborar um fluxograma que, dada a idade de um nadador, 
# classifique-o nas categorias:
# ● infantil A (5 - 7 anos); ● infantil B (8 - 10 anos);
# ● juvenil A (11 - 13 anos); ● juvenil B (14 -17 anos);
# ● adulto (maiores que 18 anos).
# Leitura da idade do nadador
idade = int(input("Digite a idade do nadador: "))

# Classificação de acordo com a idade
if 5 <= idade <= 7:
    print("Categoria: Infantil A")
elif 8 <= idade <= 10:
    print("Categoria: Infantil B")
elif 11 <= idade <= 13:
    print("Categoria: Juvenil A")
elif 14 <= idade <= 17:
    print("Categoria: Juvenil B")
else:
    print("Categoria: Adulto")

