package com.arenas.hotelalura.datos.interfaces;

import com.arenas.hotelalura.main.domain.*;
import java.sql.SQLException;

public interface TransaccionDAO {
    
    public int transaccion(HuespedDTO hd) throws SQLException;
    
}
