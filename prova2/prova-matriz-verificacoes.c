#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void questao1()
{
	printf("Nome: \n");
    char nome[100];
    printf("Digite Seu Nome inteiro: ");
    scanf(" %[^\n]", nome);

    // Verificar se o nome tem pelo menos dois nomes e a primeira letra de cada nome está em maiúsculo
    char* token = strtok(nome, " ");
    int count = 0;

    while (token != NULL)
    {
        count++;
        token = strtok(NULL, " ");
    }

    if (count >= 2 && isupper(nome[0]))
    {
        printf("Nome valido\n");
    }
    else
    {
        printf("Nome invalido\n");
    }

	printf("\n\n");
}

void questao2()
{
	printf("CPF: \n");
    char cpf[15];
    printf("Digite Seu Cpf: ");
    scanf("%s", cpf);

    // Validar se o CPF tem 11 dígitos
    if (strlen(cpf) == 11)
    {
        // Imprimir CPF formatado
        printf("CPF formatado: %.3s.%.3s.%.3s-%.2s\n", cpf, cpf + 3, cpf + 6, cpf + 9);
    }
    else
    {
        printf("Erro: CPF invalido\n");
    }
	printf("\n\n");
}

void questao3()
{
	printf("Telefone: \n");
    char telefone[20];
    printf("Digite Seu telefone no formato +55 (XX) XXXXX-XXXX: ");
    scanf(" %[^\n]", telefone);

    // Validar o formato do telefone
    // Implemente a lógica de validação aqui (pode usar expressões regulares, se desejar)

    // Imprimir apenas os números sem formatação
    char numeros[15];
    int j = 0;
    for (int i = 0; telefone[i] != '\0'; i++)
    {
        if (isdigit(telefone[i]))
        {
            numeros[j++] = telefone[i];
        }
    }
    numeros[j] = '\0';

    printf("Telefone sem formatacao: %s\n", numeros);
	printf("\n\n");
}

void questao4()
{
	printf("Matriz:\n");
    int xlin, xcol;

    printf("Digite o Numero de Linhas (max 10): ");
    scanf("%i", &xlin);

    printf("Digite o Numero de Colunas (max 10): ");
    scanf("%i", &xcol);

    if (xlin > 10 || xcol > 10)
    {
        printf("Erro: Tamanho da matriz excede o maximo permitido\n");
        return;
    }

    int x[xlin][xcol];

    for (int i = 0; i < xlin; i++)
    {
        for (int j = 0; j < xcol; j++)
        {
            printf("Digite o valor para x[%d][%d]: ", i, j);
            scanf("%i", &x[i][j]);
        }
    }

    printf("Matriz:\n");
    for (int k = 0; k < xlin; k++)
    {
        for (int l = 0; l < xcol; l++)
        {
            printf("%i\t", x[k][l]);
        }
        printf("\n");
    }

    // Encontrar e imprimir a soma dos segundos menores números de cada linha
    for (int m = 0; m < xlin; m++)
    {
        int menor = x[m][0];
        int segundoMenor = x[m][0];

        for (int n = 1; n < xcol; n++)
        {
            if (x[m][n] < menor)
            {
                segundoMenor = menor;
                menor = x[m][n];
            }
            else if (x[m][n] < segundoMenor && x[m][n] != menor)
            {
                segundoMenor = x[m][n];
            }
        }

        printf("Soma dos Menores de Cada linha para a linha %d: %d\n", m, menor + segundoMenor);
    }

    // Imprimir a transposta da matriz
    printf("Matriz Transposta:\n");
    for (int m = 0; m < xcol; m++)
    {
        for (int n = 0; n < xlin; n++)
        {
            printf("%i\t", x[n][m]);
        }
        printf("\n");
    }
}

int main()
{
    questao1();
    questao2();
    questao3();
    questao4();
    return 0;
}