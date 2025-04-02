#calculo do consumo mensal de gás
'''
consumos ate 20m^3 sao cobrados 2,00 por m^3
de 21 a 40 m^3 o preço é de 2.50 m^3
acima de 40 m^3 adicional de 3,00
disponibilidade de serviço 25,00
'''

# Entrada do consumo em m³
Consumo_Gas = float(input("Digite o consumo mensal de gás em m³: "))

disponibilidade_servico = 25.00
    
# Cálculo do valor com base nas faixas de consumo
if Consumo_Gas <= 20:
     valor_consumo = Consumo_Gas * 2.00

elif Consumo_Gas <= 40:
    valor_consumo = (20 * 2.00) + ((Consumo_Gas - 20) * 2.50)

else:
    valor_consumo = (20 * 2.00) + (20 * 2.50) + ((Consumo_Gas - 40) * 3.00)
    
# Cálculo do valor total (consumo + disponibilidade)
valor_total = valor_consumo + disponibilidade_servico
    
# Exibe o valor total
print(f"O valor total do consumo de gás é: R$ {valor_total:.2f}")


