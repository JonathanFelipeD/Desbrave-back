```mermaid
erDiagram
    usuario {
        INT idusuario
        VARCHAR nome
        VARCHAR email
        VARCHAR senha
        ENUM tipo_usuario
        INT pontuacao_total
        DATE data_criacao
    }

    curso {
        INT idcurso
        VARCHAR titulo
        VARCHAR descricao
        VARCHAR categoria
        INT carga_horaria
        ENUM status
        VARCHAR url_externa
    }

    forum {
        INT idforum
        ENUM titulo
        VARCHAR descricao
        DATE data_criacao
    }

    postagem {
        INT idpostagem
        TEXT conteudo
        DATE data_postagem
    }

    QRcode {
        INT idQRCode
        VARCHAR localizacao
        INT valor_pontos
        ENUM status
        DATE data_criacao
    }

    usuario_qrcode {
        INT idusuario_qrcode
        DATE data_escaneamento
        INT pontos_ganhos
    }

    cupom {
        INT idcupom
        VARCHAR codigo
        DOUBLE desconto
        ENUM status
    }

    parceria_cupom {
        INT idparceria_cupom
        DATE validade
        ENUM uso
    }

    parceria {
        INT idparceria
        VARCHAR nome
        ENUM tipo
        TEXT descricao
        VARCHAR contato
    }

    historico_resgate {
        INT idhistorico_resgate
        DATE data_resgate
    }

    usuario_cupom {
        INT idusuario_cupom
        DATE data_resgate
        ENUM usado
    }

    usuario ||--o{ curso : participa
    usuario ||--o{ postagem : escreve
    postagem ||--o{ forum : pertence
    usuario ||--o{ usuario_qrcode : escaneia
    usuario_qrcode ||--o{ QRcode : pertence
    usuario ||--o{ usuario_cupom : resgata
    usuario_cupom ||--o{ cupom : pertence
    cupom ||--o{ parceria_cupom : oferecido_por
    parceria_cupom ||--o{ parceria : pertence
    usuario ||--o{ historico_resgate : registra
    historico_resgate ||--o{ cupom : contem
    historico_resgate ||--o{ parceria : refere_se_a

