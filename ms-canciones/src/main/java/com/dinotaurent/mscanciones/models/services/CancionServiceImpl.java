package com.dinotaurent.mscanciones.models.services;

import com.dinotaurent.commons.models.services.CommonServiceImpl;
import com.dinotaurent.commonscancionesalbumes.models.entity.Cancion;
import com.dinotaurent.mscanciones.clients.IArtistaCancionFeingClient;
import com.dinotaurent.mscanciones.models.dao.ICancionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CancionServiceImpl extends CommonServiceImpl<Cancion, ICancionDao> implements ICancionService {
    @Autowired
    private IArtistaCancionFeingClient artistaFeingClient;

    @Override
    @Transactional(readOnly = true)
    public List<Cancion> findAll() {
        return dao.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cancion> findAll(Pageable pageable) {
        return dao.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public void eliminarArtistaCancion(Long cancionId) {
        artistaFeingClient.eliminarArtistaCancion(cancionId);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.artistaFeingClient.eliminarArtistaCancion(id);
    }
}
