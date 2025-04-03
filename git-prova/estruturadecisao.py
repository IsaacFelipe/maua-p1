# # O que são: São usadas para executar diferentes blocos de código dependendo de uma condição.

# # Sintaxe básica:

# if condição:
#     # código se a condição for verdadeira
# else:
#     # código se a condição for falsa

idade = int(input("Qual sua idade? "))
if idade >= 18:
    print("Você é maior de idade.")
else:
    print("Você é menor de idade.")


# Variáveis
# O que são: Variáveis armazenam valores e podem ser usadas ao longo do código.
# Tipos comuns: int, float, str, bool.
# Como usar: Atribuindo valores a nomes de variáveis.
# Sintaxe básica:
# nome_variavel = valor  # Atribuição de valor à variável

# Exemplo:
# Definindo variáveis
x = 10  # Atribuição de valor à variável x
y = "Olá"  # Atribuição de valor à variável y
print(x, y)  # Exibe os valores de x e y

# Listas
# O que são: Estruturas de dados que armazenam múltiplos valores em uma única variável.
# Como usar: Criando uma lista com colchetes [] e acessando elementos por índice.
# Sintaxe básica:
# lista = [valor1, valor2, valor3]  # Criação de uma lista
# Exemplo:
# Criando uma lista de frutas
frutas = ["maçã", "banana", "laranja"]  # Criação de uma lista de frutas
print(frutas)  # Exibe a lista de frutas
# Acessando elementos da lista
print(frutas[0])  # Exibe o primeiro elemento da lista (maçã)
# Adicionando elementos à lista
frutas.append("uva")  # Adiciona "uva" à lista de frutas
print(frutas)  # Exibe a lista atualizada de frutas
# Acessando elementos da lista
print(frutas[1])  # Exibe o segundo elemento da lista (banana)
# Removendo elementos da lista
frutas.remove("banana")  # Remove "banana" da lista de frutas
# Exibindo a lista atualizada
print(frutas)  # Exibe a lista atualizada de frutas


# Funções
# O que são: Blocos de código reutilizáveis que realizam uma tarefa específica.
# Como usar: Definindo uma função com def e chamando-a pelo nome.
# Exemplo:
def soma(a, b):
    return a + b  # Retorna a soma de a e b