numerosecreto = 7

while True:
    tentativa = int(input('Adivinhe o número secreto entre 1 e 10 (Digite 0 para sair): '))

    if tentativa == 0:
        print("Jogo encerrado, Até a próxima!")
        break

    if tentativa == numerosecreto:
        print("Parábens! Você acertou o número.")
        break
    else:
        print ("Tente Novamente!")