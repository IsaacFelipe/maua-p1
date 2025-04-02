#importação da boblioteca datetime

import datetime

#saber dia de hoje

hoje = datetime.date.today()

#dicionario de conversao numero mes para nome mes

meses = {
    1: 'Janeiro', 2: 'Fevereiro', 3: 'Março', 4: 'Abril', 5: 'Maio', 6:'Junho',
    7:'Julho', 8:'Agosto', 9: 'Setembro', 10: 'Outubro', 11: 'Novembro', 12: 'dezembro'
}

print(f'Hoje é dia {hoje.day} de {meses[hoje.month]} de {hoje.year}')

#saber ontem

ontem = hoje - datetime.timedelta(days=1)
print(f'Ontem: {ontem}')
print(f'Ontem foi dia {ontem.day} de {meses[ontem.month]} de {ontem.year}')

#saber amanha

amanha = hoje + datetime.timedelta(days=1)
print(f'Amanha: {amanha}')
print(f'Amanha será dia {amanha.day} de {meses[amanha.month]} de {amanha.year}')

#hora agora

hora = datetime.datetime.now()
print(hora)

#escrever um programa que adiciona 5 segundos a hora atual

hora_5_segundos = hora + datetime.timedelta(seconds=5)
print(f'Hora atual com 5 min adicionados: {hora_5_segundos}')

#escrever um codigo se diz se o ano é bissexro ou nãp
# para ser ano bissexto ele tem q ser multiplo de 4, porem nao sao multiplos de 100 exteto os multiplos de 400

ano = int(input('Digite o ano: '))

if ano % 400 == 0
    saida = "É bissexto"

elif ano % 100 == 0
    saida = "Não é bissexto"

elif ano % 4 ==0
    saida = "É bissexto" 
    ""
else:
    Saida = "Não é bissexto"

print(f"O ano {ano}{saida}.")

# ou  importando calendario

from calendar import isleap

Ano = int(input('Digite o ano: '))
if isleap(Ano):
    saida = 'É bissexto'
else:
    saida = "Não é bissexto"

print(f"O ano {Ano}{saida}.")
