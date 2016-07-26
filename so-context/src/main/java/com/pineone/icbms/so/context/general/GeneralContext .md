## GeneralContext 
- 1) GeneralContext 생성 요청 
	- 설명 : Context 생성 요청을 받고 DeviceObject(VO, CVO List) 리턴
	- 참조 객체  : UserInterface, DeviceCenter
	 		
	 		List<DeviceObject> requestContextMaking()

- 2) ConceptServiceList 조회
	- 설명 : 선택한 DeviceObject의 ConceptSerivceList를 조회
	- 참조 객체 : UserInterface, DeviceCenter
	
			List<ConceptSerivce> retrieveConceptService(DeviceObject deviceObject)

- 3) SDA에 GeneralContext 등록
	- UserInterface에서 GeneralContext 생성 정보를 모두 수신 후 SDA에 등록
	- 참조 객체 : (SDA)ContextInterface, UserInterface
	
			String(GeneralContextId) registerGeneralContext
			(String name, DeviceObject deviceObject, ConceptService conceptService
			int minValue, int maxValue)
	
- 4) DB에 GeneralContext 저장
	- DB에 SDA에 등록한 이후의 GeneralContext를 등록한다(SDA에 등록후 수신 받은 Id 포함)
	- 참조 객체 : DBInterface
	
			void createGeneralContext(GeneralContext generalContext)

- 5) DB에서 GeneralContextList 조회
	- SO에서 관리하고 있는 GeneralContext 목록 조회, ContextModel생성시 사용
	- 참조 객체 : UserInterface, DBInterface

			List<GeneralContext> retrieveGeneralContextList()
			
- 6) SDA의 GeneralContextList 조회 
	- SDA에서 관리하는 GeneralContext 목록 조회, SO에서 주기적인 동기화를 위해 사용할 예정
	- 참조 객체 : (SDA)ContextInterface
	
			List<GeneralContext> retrieveGeneralContextListFromSDA()
			
- 7) GeneralContext 상세 정보 요청
	- SDA or DB 에 저장되어 있는  Context의 상세 정보를 요청
	- 참조 객체 : UserInterface , (SDA)ContextInterface
	
			GeneralContext retrieveGeneralContextDetail(String id or name)
	

## 연관 모듈
### UserInterface
- 1) GeneralContext 생성 요청
	- GET방식
	- /requirement/generalcontext
	
			List<DeviceObject> generalContextMakingController() - 1번 이용

- 2) ConceptService 조회
	- GET방식
	- /inquiry/conceptservice/(deviceObject)
	
			List<ConceptService> retrieveConceptServiceController
			(DeviceObject deviceObject) - 2번 이용

- 3) GeneralContext 등록
	- POST방식
	- /registration/generalcontext
	
			(Response) registerGeneralContextController
			(String name, DeviceObject deviceObject, ConceptService conceptService
			int minValue, int maxValue) - 3번 이용

- 4) GeneralContextList 조회
	- GET 방식
	- /inquiry/generalcontext
	
			List<GeneralContext> retrieveGeneralContextListController() - 5번 이용
	
- 5) GeneralContext 상세 조회
	- GET 방식
	- /inquiry/generalcontext/{contextName}
	
			GeneralContext retrieveGeneralContextController(String ContextName) 
			- 7번 이용

### Device 관련(Dummy Class - DeviceCenter)
- 1) DeviceObjectList 조회
	- VO, CVO List return
	
			List<DeviceObject> retrieveDeviceObjectList() - 1번에서 이용
- 2) ConceptServiceList 조회
	- DeviceObject에 관련된 ConceptSerivceList 조회
	
			List<ConceptService> retrieveConceptServiceList() - 2번에서 이용
			
### DB Interface(Temp - Map 이용)
- 1) GeneralContext 저장
	- DB에 GeneralContext 저장
		
			void createGeneralContext(GeneralContext generalContext) - 4번에서 이용
			
- 2) GeneralContextList 조회
	- SO에서 관리하는 GeneralContextList 조회
			
			List<GeneralContext> retrieveGeneralContextList() - 5번에서 이용
			
### (SDA) ContextInterface(Dummy Class - SDAModule)
- 1) SDA에 GeneralContext 등록
	- 상황인지 하는 시스템의 API를 이용해서 GeneralContext 등록
	
			String(GeneralContextId) registerGeneralContext(GeneralContext
			 generalContext) - 3번에서 이용
	
- 2) GeneralContextList 조회
	- 상황인지 하는 시스템의 API를 이용해서 등록되어있는 GeneralContext의 리스트를 조회

			List<GeneralContext> retrieveGeneralContextListFromSDA() - 6번에서 이용
			
- 3) GeneralContext 조회
	- 상황인지 하는 시스템의 API를 이용해서 등록되어 있는 GeneralContext의 상세정보를 조회
		
			GeneralContext retrieveGeneralContextDetail(String contextName)
			- 7번에서 이용