package com.coamctech.bxloan.service.approval.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.InfoCellConfigDao;
import com.coamctech.bxloan.dao.InfoInputConfigDao;
import com.coamctech.bxloan.dao.InfoValuesDao;
import com.coamctech.bxloan.entity.InfoCellConfig;
import com.coamctech.bxloan.entity.InfoInputConfig;
import com.coamctech.bxloan.entity.InfoValues;
import com.coamctech.bxloan.service.approval.ApprovalApplyInfoService;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoCellVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoLineGroupVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoLineVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoTableVO;

@Service
@Transactional
public class ApprovalApplyInfoServiceImpl implements ApprovalApplyInfoService {

	@Autowired
	private DynamicQuery query;

	@Autowired
	private InfoCellConfigDao appInfoCellDao;

	@Autowired
	private InfoInputConfigDao appInfoInputDao;

	@Autowired
	private InfoValuesDao appInfoValuesDao;

	@Override
	@SuppressWarnings("serial")
	public Map<String, String> findValuesBySubjectId(Long subjectId) {

		final List<Object[]> resultSet = this.query.query(Object[].class,
				"SELECT name,value FROM InfoValues WHERE subjectId=?1 ",
				subjectId);
		if (CollectionUtils.isNotEmpty(resultSet)) {
			return new HashMap<String, String>() {
				{
					for (Object[] obj : resultSet) {
						put(obj[0] == null ? "" : obj[0].toString(),
								obj[1] == null ? "" : obj[1].toString());
					}
				}
			};
		}
		return Collections.emptyMap();
	}

	@Override
	public ApplyInfoTableVO getTableByType(String tableTypeCd) {

		List<InfoCellConfig> cells = this.query.query(InfoCellConfig.class,
				"FROM InfoCellConfig t WHERE t.tableTypeCd=?1", tableTypeCd);

		List<InfoInputConfig> inputs = this.query
				.query(InfoInputConfig.class,
						"FROM InfoInputConfig t WHERE t.cellId in (SELECT id FROM InfoCellConfig WHERE tableTypeCd=?1)",
						tableTypeCd);

		if (CollectionUtils.isEmpty(cells)) {
			return new ApplyInfoTableVO();
		}

		ApplyInfoTableVO result = this.buildApplyInfoTableVO(cells, inputs);

		return result;
	}

	private ApplyInfoTableVO buildApplyInfoTableVO(
			List<InfoCellConfig> cellsList, List<InfoInputConfig> inputsList) {

		ApplyInfoTableVO table = new ApplyInfoTableVO();
		table.setTableHead(cellsList.get(0).getTableTypeName());
		int maxColumn = 0;

		for (InfoCellConfig cell : cellsList) {
			boolean hasGroup = false;

			for (ApplyInfoLineGroupVO lineGroup : table.getLinegroupList()) {
				if (lineGroup.getLineGroupCd().equals(cell.getRowGroupCd())) {
					hasGroup = true;
					Integer cellCount = putCellToLineGroup(cell, lineGroup,
							inputsList);
					if (cellCount > maxColumn) {
						maxColumn = cellCount;
					}
				}
			}

			if (!hasGroup) {
				ApplyInfoLineGroupVO group = new ApplyInfoLineGroupVO();
				group.setLineGroupCd(cell.getRowGroupCd());
				group.setTitle(cell.getRowGroupName());

				ApplyInfoLineVO line = this.buildLineVO(cell, inputsList);
				group.getLines().add(line);
				if (line.getCellList().size() > maxColumn) {
					maxColumn = line.getCellList().size();
				}
				table.getLinegroupList().add(group);
			}
		}

		table.setColumnCount(maxColumn);

		this.sortTable(table);
		return table;
	}

	private void sortTable(ApplyInfoTableVO table) {
		for (ApplyInfoLineGroupVO group : table.getLinegroupList()) {
			for (ApplyInfoLineVO line : group.getLines()) {
				for (ApplyInfoCellVO cell : line.getCellList()) {
					Collections.sort(cell.getInputList(),
							new Comparator<InfoInputConfig>() {
								@Override
								public int compare(InfoInputConfig o1,
										InfoInputConfig o2) {
									return o1.getSortId() < o2.getSortId() ? -1
											: 1;
								}
							});
				}
				Collections.sort(line.getCellList(),
						new Comparator<ApplyInfoCellVO>() {
							@Override
							public int compare(ApplyInfoCellVO o1,
									ApplyInfoCellVO o2) {
								return o1.getCell().getSortId() < o2.getCell()
										.getSortId() ? -1 : 1;
							}
						});
			}
			Collections.sort(group.getLines(),
					new Comparator<ApplyInfoLineVO>() {
						@Override
						public int compare(ApplyInfoLineVO o1,
								ApplyInfoLineVO o2) {
							return o1.getLineCd() < o2.getLineCd() ? -1 : 1;
						}
					});
		}

		Collections.sort(table.getLinegroupList(),
				new Comparator<ApplyInfoLineGroupVO>() {
					@Override
					public int compare(ApplyInfoLineGroupVO o1,
							ApplyInfoLineGroupVO o2) {
						return o1.getLineGroupCd() < o2.getLineGroupCd() ? -1
								: 1;
					}
				});

	}

	private Integer putCellToLineGroup(InfoCellConfig cell,
			ApplyInfoLineGroupVO lineGroup, List<InfoInputConfig> inputsList) {

		for (ApplyInfoLineVO line : lineGroup.getLines()) {
			if (line.getLineCd().equals(cell.getRowCd())) {
				ApplyInfoCellVO cellVO = buildCellVO(cell, inputsList);
				line.getCellList().add(cellVO);
				return line.getCellList().size();
			}
		}

		ApplyInfoLineVO line = buildLineVO(cell, inputsList);
		lineGroup.getLines().add(line);
		return line.getCellList().size();
	}

	private ApplyInfoLineVO buildLineVO(InfoCellConfig cell,
			List<InfoInputConfig> inputsList) {
		ApplyInfoLineVO line = new ApplyInfoLineVO();
		line.setLineCd(cell.getRowCd());
		line.getCellList().add(this.buildCellVO(cell, inputsList));
		return line;
	}

	private ApplyInfoCellVO buildCellVO(InfoCellConfig cell,
			List<InfoInputConfig> inputsList) {
		ApplyInfoCellVO cellVO = new ApplyInfoCellVO();
		cellVO.setCell(cell);
		for (InfoInputConfig input : inputsList) {
			if (input.getCellId().equals(cell.getId())) {
				cellVO.getInputList().add(input);
			}
		}
		return cellVO;
	}

	@Override
	public void saveValues(List<InfoValues> valueList, String tableTypeCd) {
		this.query
				.nativeExecuteUpdate(
						"DELETE FROM Info_values WHERE subject_id = ?1 and name in(select icc.cell_input_name from info_cell_config icc where icc.table_type_cd in ("
								+ tableTypeCd + "))", valueList.get(0)
								.getSubjectId());
		this.appInfoValuesDao.save(valueList);
	}

}
