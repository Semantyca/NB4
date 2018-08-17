package com.semantyca.officeframe.modules.reference.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.officeframe.modules.reference.init.ModuleConst;

import javax.persistence.*;

@Entity
@Cacheable(true)
@Table(name = ModuleConst.CODE + "__departmenttypes", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NamedQuery(name = "DepartmentType.findAll", query = "SELECT m FROM DepartmentType AS m ORDER BY m.regDate")
public class DepartmentType extends SimpleReferenceEntity {

}
