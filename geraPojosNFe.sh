#!/bin/bash
DESTINO="generated"
SCHEMA_PATH="schemas/PL_006q"

xjc -d $DESTINO $SCHEMA_PATH/enviNFe_v2.00.xsd $SCHEMA_PATH/nfe_v2.00.xsd $SCHEMA_PATH/leiauteNFe_v2.00.xsd $SCHEMA_PATH/tiposBasico_v1.03.xsd

