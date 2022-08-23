package com.springbootmysql.crud.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springbootmysql.crud.bean.DataTableRequestBean;
import com.springbootmysql.crud.bean.DataTableResponseBean;
import com.springbootmysql.crud.bean.EmployeeBean;
import com.springbootmysql.crud.bean.JqxGridDatatableResponseBean;
import com.springbootmysql.crud.bean.ResponseBean;
import com.springbootmysql.crud.constant.GlobalConstant;
import com.springbootmysql.crud.constant.MessageConstant;
import com.springbootmysql.crud.constant.Status;
import com.springbootmysql.crud.model.Employee;
import com.springbootmysql.crud.repository.EmployeeDao;
import com.springbootmysql.crud.repository.common.impl.CustomEmployeeDaoWrapperImpl;
import com.springbootmysql.crud.service.EmployeeService;
import com.springbootmysql.crud.service.common.impl.BaseServiceImpl;
import com.springbootmysql.crud.utility.common.ExcelUtil;
import com.springbootmysql.crud.utility.exception.EmployeeNotFoundException;

@Service
@Qualifier("employeeServiceImpl")
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
	protected final String SORT_DIRECTION_ASC = "ASC";
	protected final String SORT_DIRECTION_DESC = "DESC";
	
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CustomEmployeeDaoWrapperImpl employeeDaoWrapperImpl;
	
	private static Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public ResponseBean saveEmployee(Employee employee) {
		try {
			if (employee == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Data Is Null").build();
			}
			
			Employee employeeBean = employeeDao.save(employee);
			return ResponseBean.builder().status(Status.SUCCESS).message("Record Added Succesfully")
					.response(employeeBean).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);			
			return ResponseBean.builder().status(Status.FAIL)
					.message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	@Override
	public ResponseBean updateEmployee(Employee employee) {
		try {
			if (employee == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Data Is Null").build();
			}

			if (Long.valueOf(employee.getId()) == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Employee> findById = employeeDao.findById(employee.getId());
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			Employee employeeBean = employeeDao.save(employee);
			return ResponseBean.builder().status(Status.SUCCESS).message("Record Added Succesfully")
					.response(employeeBean).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean getAllEmployee() {
		try {
			long count=0;
			 count = employeeDao.count();
			
			if(count<=0) {
				 count=0l;
			}
			List<Employee> empolyeeList = employeeDao.findAll();
			if (empolyeeList == null) {
				
				return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();

			}
			return ResponseBean.builder().status(Status.SUCCESS).response(JqxGridDatatableResponseBean.<Employee>builder().totalRecords(count).rows(empolyeeList).build()).build();


		} catch (Exception e) {

			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
	}

	@Override
	public ResponseBean findEmployeeById(Long id) {
		try {
			if (id == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Employee> findById = employeeDao.findById(id);
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).response(findById.get()).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();

		}
	}

	@Override
	public ResponseBean deleteEmployeeById(Long id) {
		try {
			if (id == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Employee> findById = employeeDao.findById(id);
			if (findById == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			employeeDao.deleteById(findById.get().getId());

			return ResponseBean.builder().status(Status.SUCCESS).message("Record Deleted Sccessfully!").build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
	}

	@Override
	public ResponseBean findByDepartment(String department) {
		try {
			List<EmployeeBean> empolyeeList = employeeDao.findByDepartment(department);
			if (empolyeeList != null && empolyeeList.size() > 0) {
				return ResponseBean.builder().status(Status.SUCCESS).response(empolyeeList).build();

			}

			return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
	}

	@Transactional
	public ResponseBean increaseSalaryByTenPercentageHavingDepartmentCricketElseFivePercentage() {
		try {
			List<Employee> empolyeeList = employeeDao.findAll();
			List<Employee> updatedList = new ArrayList<Employee>();
			if (empolyeeList != null && empolyeeList.size() > 0) {
				List<Employee> newEmployeeList = empolyeeList.stream().map((e) -> {
					if (e.getDepartment().equalsIgnoreCase("cricket")) {
						e.setSalary(e.getSalary() * 1.1);
					} else {
						e.setSalary(e.getSalary() * 1.05);
					}
					return e;
				}).collect(Collectors.toList());

				for (Employee employee : newEmployeeList) {
					Employee save = employeeDao.save(employee);
					updatedList.add(save);
				}

				return ResponseBean.builder().status(Status.SUCCESS).response(updatedList).build();

			}

			return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public Employee findEmployeeByEmail(String email) {
		Employee employee = employeeDao.findEmployeeByEmail(email);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee Not Found");
		}
		return employee;
	}

	@Override
	public ResponseBean downloadEmployeeReport() {

		try {
			List<Employee> empolyeeList = employeeDaoWrapperImpl.getAllEmployeeList();
			if (empolyeeList != null && empolyeeList.size() > 0) {
				String fileName = "employee.xlsx";
				String header[] = new String[] { "Employee Id", "Employee Name", "Employee Email",
						"Employee Department", "Employee Salary", "Employee Is Senior" };
				List<String[]> data = empolyeeList.stream().map((employee) -> {
					String[] employeeArray = new String[] { employee.getId()+"", employee.getName(),
							employee.getEmail(), employee.getDepartment(), employee.getSalary() + "",
							(employee.isSenior() ? "Yes" : "No") };
					return employeeArray;
				}).collect(Collectors.toList());

				SXSSFWorkbook sXSSFWorkbook = ExcelUtil.dataToExcellSheet(header, data, fileName);

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				sXSSFWorkbook.write(bos);
				sXSSFWorkbook.close();
				bos.close();
				return ResponseBean.builder().status(Status.SUCCESS).response(bos.toByteArray()).build();

			}

			return ResponseBean.builder().status(Status.FAIL)
					.message(propertyService.getMessage(MessageConstant.RECORD_NOT_FOUND)).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL)
					.message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	@Override
	public ResponseBean uploadEmployeeExcel(MultipartFile file) {
		try {
			  System.out.println(file.getInputStream().available());
            String fileType=GlobalConstant.EXCEL_TYPE;
			if(!file.getContentType().equals(fileType)) {
				return ResponseBean.builder().status(Status.FAIL)
						.message(propertyService.getMessage(MessageConstant.PROVIDE_VALID_EXCEL_CONTENTTYPE)).build();
			}
			
			List<Employee> convertExcelToListOfObject = convertExcelToListOfObject(file.getInputStream());
//                //String path="Downloads/employees.xlsx";
//                //InputStream ExcelFileToRead = new FileInputStream("C:/Users/HP/Downloads/employees.xlsx");
//			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//               // XSSFWorkbook workbook = new XSSFWorkbook(ExcelFileToRead);
//			XSSFSheet sheet = workbook.getSheet("employee");
//			Iterator<Row> rows = sheet.iterator();
//			List<Employee> employeeList = new ArrayList<>();
//			int rowNumber = 0;
//			while (rows.hasNext()) {
//				Row currentRow = rows.next();
//				if (rowNumber == 0) {
//					rowNumber++;
//					continue;
//				}
//
//				Iterator<Cell> cellsInRow = currentRow.iterator();
//				Employee employee = new Employee();
//				int cellIdx = 0;
//				while (cellsInRow.hasNext()) {
//					Cell currentCell = cellsInRow.next();
//					switch (cellIdx) {
//					case 0:
//						employee.setId((long) currentCell.getNumericCellValue());
//						break;
//					case 1:
//						employee.setName(currentCell.getStringCellValue());
//						break;
//					case 2:
//						employee.setEmail(currentCell.getStringCellValue());
//						break;
//					case 3:
//						employee.setDepartment(currentCell.getStringCellValue());
//						break;
//
//					case 4:
//						employee.setSalary((double) currentCell.getNumericCellValue());
//						break;
//
//					case 5:
//						boolean senior = currentCell.getStringCellValue().equalsIgnoreCase("Yes") ? true : false;
//						employee.setSenior(senior);
//						break;
//
//					default:
//						break;
//					}
//					cellIdx++;
//					
//				}
//				employeeList.add(employee);
//			}
//			
//			workbook.close();
//		
		if(convertExcelToListOfObject!=null && convertExcelToListOfObject.size()>0) {
			    List<Employee> emloyeeBeanList = employeeDao.saveAll(convertExcelToListOfObject);
			return ResponseBean.builder().status(Status.SUCCESS)
					.response(emloyeeBeanList).message(propertyService.getMessage(MessageConstant.RECORD_ADDED_SUCCESSFULLY)).build();
			}
			return ResponseBean.builder().status(Status.FAIL)
					.message("Processing Fail").build();
		  }

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL)
					.message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	public List<Employee> convertExcelToListOfObject(InputStream inputStream) throws IOException {
		Workbook workbook=null;
		System.out.println(inputStream.available());
		try  {
			System.out.println(inputStream);
			workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Employee> employeeList = new ArrayList<>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();
				Employee employee = new Employee();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					currentCell.setCellType(CellType.STRING);
					switch (cellIdx) {
					case 0:
						employee.setId(Long.parseLong(currentCell.getStringCellValue()) );
						break;
					case 1:
						
						employee.setName(currentCell.getStringCellValue());
						break;
					case 2:
						employee.setEmail(currentCell.getStringCellValue());
						break;
					case 3:
						employee.setDepartment(currentCell.getStringCellValue());
						break;

					case 4:
						employee.setSalary(Double.parseDouble(currentCell.getStringCellValue()));
						break;

					case 5:
						boolean senior = currentCell.getStringCellValue().equalsIgnoreCase("Yes") ? true : false;
						employee.setSenior(senior);
						break;

					default:
						break;
					}
					cellIdx++;
					
				}
				employeeList.add(employee);
			}
			
			return employeeList;

		}

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public ResponseBean getAllEmployees(DataTableRequestBean dataTableRequestBean) {
		try {
			if(dataTableRequestBean!=null) {
				dataTableRequestBean.setSearchText(dataTableRequestBean.getSearch().getValue());
	            dataTableRequestBean.setSortableColumn(dataTableRequestBean.getColumns().get(dataTableRequestBean.getOrder().get(0).getColumn()).getName());
	            
	            Pageable page = PageRequest.of(
	            		dataTableRequestBean.getStart(), 
	            		dataTableRequestBean.getLength())
	            		.withSort(Sort.by(dataTableRequestBean.getOrder().get(0).getDir().equalsIgnoreCase(SORT_DIRECTION_ASC)?Sort.Direction.ASC:Sort.Direction.DESC,dataTableRequestBean.getSortableColumn()));
	             if(dataTableRequestBean.getSearchText()!=null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
	            	  Page<Employee> allEmployees = employeeDao.getAllEmployees(dataTableRequestBean.getSearchText(),dataTableRequestBean.getSortableColumn(),page);
		              long count = employeeDao.count();
					  return ResponseBean.builder().status(Status.SUCCESS).response(DataTableResponseBean.<Employee>builder().draw(dataTableRequestBean.getDraw()).recordsTotal((int)count).recordsFiltered(allEmployees.toList().size()).data(allEmployees.toList()).build()).build();
	             }
	             Page<Employee> findAll = employeeDao.findAll(page);
	             long count = employeeDao.count();
				  return ResponseBean.builder().status(Status.SUCCESS).response(DataTableResponseBean.<Employee>builder().draw(dataTableRequestBean.getDraw()).recordsTotal((int)count).recordsFiltered((int)count).data(findAll.toList()).build()).build();

			}
			return ResponseBean.builder().status(Status.FAIL)
					.message(propertyService.getMessage(MessageConstant.RECORD_NOT_FOUND)).build();
			
		}catch(Exception e)  {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL)
					.message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}
		
	}
}
