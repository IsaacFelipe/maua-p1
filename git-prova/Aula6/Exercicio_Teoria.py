'''programa para converter  celcius para fahreinheint'''

f = c*1.8+32

try:
    c = float(input('digite aqui um temperatura em celcius'))
except ValueError:
    print('o valor inserido é abaixo do zero absoluto, insira outro')


'''programa para converter metros para centímetros e vice-versa'''
m = float(input('insira um valor em metros: '))
print(f'o valor inserido em centímetros equivale a {m*100}')

c = float(input('agora insira umm valor em centímetros'))
print (f'o valor inserido em metros equivale a {c*0.01}') 


'''programa para calcular a área do círculo usando o raio'''
r = float(input('insira um valor para o raio de um círculo:'))
print (f'a área de um círculo de raio {r} equivale a {(3.14159*r**2):.3f}')

'''programa para calcular o total do salário no final do mês'''
valor = float(input('digite o valor de sua hora de trabalho: '))
hora = float(input('digite quantas horas você trabalha por mês'))
print (f'o seu salário ao final do mês será equivalente a R${valor*hora}')