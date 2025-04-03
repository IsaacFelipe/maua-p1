# O que é: A biblioteca turtle é usada para criar gráficos e desenhos em Python.

# Como usar: Você controla uma "tartaruga" que desenha no espaço da tela.

import turtle
!pip import turtle  # Importa a biblioteca turtle
!pip install turtle  # Instala a biblioteca turtle, se necessário
!pip show turtle  # Mostra informações sobre a biblioteca turtle
!pip uninstall turtle  # Desinstala a biblioteca turtle, se necessário
turtle.Screen()  # Cria uma tela para a tartaruga desenhar

t = turtle.Turtle()  # Cria a tartaruga
t.shape("turtle")  # Define a forma da tartaruga como uma tartaruga
t.color("green")  # Define a cor da tartaruga como verde
t.pensize(5)  # Define a espessura do traço da tartaruga
t.speed(1)  # Define a velocidade da tartaruga (1 é lento, 10 é rápido)
t.penup()  # Levanta o lápis da tartaruga (não desenha enquanto se move)
t.goto(-50, 0)  # Move a tartaruga para a posição (-50, 0)
t.pendown()  # Abaixa o lápis da tartaruga (começa a desenhar)
t.forward(100)  # Move a tartaruga para frente 100 unidades
t.right(90)  # Gira a tartaruga 90 graus à direita
t.forward(100)  # Move a tartaruga novamente
turtle.done()  # Finaliza o desenho
