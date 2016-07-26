## ContextModel
- 1) ContextModel 생성 요청 
	- 설명 : ContextModel 생성 요청을 받고 GeneralContextList 리턴
	- 참조 객체  : UserInterface, GeneralContext
	 		
	 		List<GeneralContextList> retrieveContextModelList

- 2) Domain 조회
	- 설명 : ContextModel의 domain을 결정하기 위해 domainList를 조회
	- 참조 객체 : UserInterface, Domain
	
			List<Domain> retrieveDomainList()

- 3) ContextType 조회
	- ContextModel의 ContextType(Emergency, Schedule)을 결정하기 위해 contextTypeList를 조회  
	- 참조 객체 : ContextType, UserInterface
	
			List<ContextType> retrieveContextTypeList()

- 4) SDA에 ContextModel 등록
	- UserInterface에서 ContextModel 등록정보 를 모두 수신 후 SDA에 등록
	- 참조 객체 : (SDA)ContextInterface, UserInterface
	
			String(contextModelId) registerContextModel
			(String name, List<GeneralContext> generalContextList, 
			ContextType contextType, Domain domain)
	
- 5) DB에 ContextModel 저장
	- DB에 SDA에 등록한 이후의 ContextModel을 등록(SDA에 등록후 수신 받은 Id 포함)
	- 참조 객체 : DBInterface
	
			void createContextModel(ContextModel contextModel)

- 6) DB에서 ContextModelList 조회
	- SO에서 관리하고 있는 ContextModel 목록 조회, Profile 생성시 사용
	- 참조 객체 : UserInterface, DBInterface

			List<ContextModel> retrieveContextModelList()
			
- 7) SDA의 ContextModelList 조회 
	- SDA에서 관리하는 ContextModel 목록 조회, SO에서 주기적인 동기화를 위해 사용할 예정
	- 참조 객체 : (SDA)ContextInterface
	
			List<ContextModel> retrieveContextModelListFromSDA()
			
- 8) ContextModel 상세 정보 요청
	- SDA or DB 에 저장되어 있는  ContextModel의 상세 정보를 요청
	- 참조 객체 : UserInterface , (SDA)ContextInterface
	
			ContextModel retrieveContextModelDetail(String id or name)
	

## 연관 모듈
### UserInterface
- 1) ContextModel 생성 요청
	- GET방식
	- /requirement/ContextModel
	
			List<GeneralConetext> ContextModelMakingController() - 1번 이용

- 2) Domain 조회
	- GET방식
	- /inquiry/domain
	
			List<Domain> retrieveDomainController() - 2번 이용

- 3) ContextType 조회
	- GET방식
	- /inquiry/contexttype
	
			List<ContextType> retrieveContextTypeController() - 3번 이용
			
- 4) ContextModel 등록
	- POST방식
	- /registration/ContextModel
	
			(Response) registerContextModelController
			(ContextModel contextModel) - 4번 이용

- 5) ContextModelList 조회
	- GET 방식
	- /inquiry/ContextModel
	
			List<ContextModel> retrieveContextModelListController() - 7번 이용
	
- 6) ContextModel 상세 조회
	- GET 방식
	- /inquiry/ContextModel/{contextModelName}
	
			ContextModel retrieveContextModelController(String ContextModelName) 
			- 8번 이용

### Domain 관련(Domain Interface이용, 참조 경로에 따라 Domain_SO or SDA 이용
- 1) Domain 조회
	- DomainList return
	
			List<Domain> retrieveDomainList() - 2번에서 이용
			
### ContextType 관련(ContextType class이용)
- 1) ContextType 조회
	- ContextTypeList return
	
			List<ContextType> retrieveContextTypeList() - 3번에서 이용
			
### DB Interface(Temp - Map 이용)
- 1) ContextModel 저장
	- DB에 ContextModel 저장
		
			void createContextModel(ContextModel ContextModel) - 5번에서 이용
			
- 2) ContextModelList 조회
	- SO에서 관리하는 ContextModelList 조회
			
			List<ContextModel> retrieveContextModelList() - 6번에서 이용
			
### (SDA) ContextInterface(Dummy Class - SDAModule)
- 1) SDA에 ContextModel 등록
	- 상황인지 하는 시스템의 API를 이용해서 ContextModel 등록
	
			String(ContextModelId) registerContextModel(ContextModel
			 ContextModel) - 4번에서 이용
	
- 2) ContextModelList 조회
	- 상황인지 하는 시스템의 API를 이용해서 등록되어있는 ContextModel의 리스트를 조회

			List<ContextModel> retrieveContextModelListFromSDA() - 7번에서 이용
			
- 3) ContextModel 조회
	- 상황인지 하는 시스템의 API를 이용해서 등록되어 있는 ContextModel의 상세정보를 조회
		
			ContextModel retrieveContextModelDetail(String contextName)
			- 8번에서 이용

- 4) ContextModel 상황 발생 여부 질의(스케줄러에서 사용)
	- 등록된 Profile의 상황(ContextModel)이 인지 되었는지 질의
		
			List<Domain> isHappenContextModel(ContextModel contextModel)
			- 스케줄러 or 프로파일에서 이용  