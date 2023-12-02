package br.unitins.tp1.pizzaria.model;

public enum NivelAcesso {
    ADMIN,
    GERENTE,
    SUPERVISOR,
    ATENDENTE;

    public static class Role{
        public static final String ADMIN = "ADMIN";
        public static final String GERENTE = "GERENTE";
        public static final String SUPERVISOR = "SUPERVISOR";
        public static final String ATENDENTE = "ATENDENTE";
    }
}
