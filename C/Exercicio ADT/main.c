#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "main.h"

Conta* criarConta(int id, char nome[], float saldo) {
    Conta* novaConta = (Conta*)malloc(sizeof(Conta));
    if (novaConta == NULL) {
        printf("Erro!!!\n");
        return NULL;
    }

    novaConta->id = id;
    novaConta->nome = strdup(nome);
    novaConta->saldo = saldo;

    return novaConta;
}

void depositar(Conta* conta, float valor) {
    if (conta != NULL && valor > 0) {
        conta->saldo += valor;
        printf("depositado: %.2f na conta: %s. saldo atual: %.2f\n", valor, conta->nome, conta->saldo);
    } else {
        printf("deposito invalido.\n");
    }
}

void sacar(Conta* conta, float valor) {
    if (conta != NULL && valor > 0 && conta->saldo >= valor) {
        conta->saldo -= valor;
        printf("sacado: %.2f da conta %s saldo atual: %.2f\n", valor, conta->nome, conta->saldo);
    } else {
        printf("saque invalido.\n");
    }
}

void transferir(Conta* conta_de, Conta* conta_para, float valor) {
    if (conta_de != NULL && conta_para != NULL && valor > 0 && conta_de->saldo >= valor) {
        sacar(conta_de, valor);
        depositar(conta_para, valor);
        printf("transferido: R$ %.2f de %s para %s.\n", valor, conta_de->nome, conta_para->nome);
    } else {
        printf("transferencia invalida.\n");
    }
}
