package com.dinotaurent.msgeneros.models.services;

import com.dinotaurent.commons.models.services.CommonServiceImpl;
import com.dinotaurent.commonsartistasgeneros.models.entity.Genero;
import com.dinotaurent.msgeneros.models.dao.IGeneroDao;
import org.springframework.stereotype.Service;


@Service
public class GeneroServiceImpl extends CommonServiceImpl<Genero, IGeneroDao> implements IGeneroService {
}
