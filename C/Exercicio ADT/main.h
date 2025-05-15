#ifndef CONTA_H
#define CONTA_H

typedef struct {
    int id;
    char* nome;
    float saldo;
} Conta;

Conta* criarConta(int id, char nome[], float saldo);
void depositar(Conta* conta, float valor);
void sacar(Conta* conta, float valor);
void transferir(Conta* conta_de, Conta* conta_para, float valor);

#endif
