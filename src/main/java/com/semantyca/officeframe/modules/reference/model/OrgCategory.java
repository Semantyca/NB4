package com.semantyca.officeframe.modules.reference.model;


import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.officeframe.modules.reference.init.ModuleConst;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(true)
@Table(name = ModuleConst.CODE + "__org_categories", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class OrgCategory extends SimpleReferenceEntity {

}
