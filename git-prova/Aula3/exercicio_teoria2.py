''' exercicios aula teorica 03 '''
''' 1)considere a variavel valor = 49.99.
Usando o operador % escreva o codigo para que a saida da função print seja "O preço do produto é R$49.99'''

valor = 49.99

print('O preço do produto é %.2f' %valor) # para arredondar colocaria %.2f (duas casas decimais após a virgula)
# Explicação:
# valor = 49.99:
# A variável valor recebe o número 49.99. Esse valor representa o preço de um produto, por exemplo.
# 'O preço do produto é %.2f' % valor:
# Esse trecho usa uma técnica chamada formatação de strings em Python, que permite inserir valores dentro de uma string de maneira específica.
# O %.2f dentro da string indica como o valor da variável valor será formatado:
# %: Esse símbolo é utilizado para indicar que haverá uma formatação.
# .2f: O .2f indica que o número deve ser formatado como um float (número decimal) e arredondado para 2 casas decimais. Ou seja, o valor será mostrado com 2 casas após a vírgula.
# % valor: Esse símbolo % é usado para indicar que o valor da variável valor deve ser inserido na string no lugar do %.2f.
# Portanto, quando o código é executado, o Python substitui o %.2f pelo valor de valor (49.99) formatado com 2 casas decimais.
# Depois do %.2f, usamos o operador % para passar o valor de valor como argumento que será inserido na string. Neste caso, o número 49.99 será colocado no lugar de %.2f.



'''Considere Cor = 'vermelho' e preço = 75000.00. 
Usando .format escreva a saida ; ''O carro vermelho custa R$75000.00'' '''

Cor = 'Vemelho'
preço = 75000.00

print("O Carro {}, custa R$ {:.2f}.".format(Cor,preço))

'''Considere nome = "Joao", Idade= 25, Cidade = "São Paulo".
Usando  f-string, escreva a saida: "Joaõ tem 25 anos e mora em São Paulo" '''

Nome = "João"
Idade = 25
Cidade = "São Paulo"

print(f"{Nome} tem {Idade} anos e mora em {Cidade}")