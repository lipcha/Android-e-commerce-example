package com.foxkiev.app.model.models.query;

import com.foxkiev.app.model.models.IdModel;

/**
 * Created by lipcha on 26.04.18.
 */

public interface Query<MODEL extends IdModel> {

    boolean contains(MODEL model);
}
