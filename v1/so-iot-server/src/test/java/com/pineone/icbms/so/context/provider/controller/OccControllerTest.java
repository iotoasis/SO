package com.pineone.icbms.so.context.provider.controller;

/**
 * Created by Melvin on 2015. 12. 22..
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = WebAppContext.class)
public class OccControllerTest {
//    public static final String testContextID = "CM-1-1-110";
//    public static final String classRoomURI = "xxx.xxx.xx/xx/xx/classroom101";
//    public static final String testURI = "/resource/occ";
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    DefaultDomain domain = new DefaultDomain();
//
//    ArrayList<DefaultDomain> domains = new ArrayList<>();
//
//
//
//    @Before
//    public void setUp() throws Exception
//    {
//
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext).build();
//    }
//
//    public ResultActions getResultActions(Object occurrence, String url)
//            throws Exception
//    {
//        return mockMvc.perform(MockMvcRequestBuilders.post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(TestUtil.convertObjectToJsonBytes(occurrence)));
//    }
//
//    @Test
//    public void happyCaseTest() throws Exception
//    {
//        /**
//         * Prepare Resources</BR>
//         */
//
//        domain.setId(classRoomURI);
//        domains.add(domain);
//        String soURI = testURI;
//        DefaultOccurrence occurrence = new DefaultOccurrence("occ",testContextID, domains,
//                "20151110T153028");
//
//        /**
//         * Test Process</BR>
//         */
//        ResultActions resultActions = getResultActions(occurrence, soURI);
//        /**
//         * Expect Result</BR>
//         */
//        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(
//                MockMvcResultMatchers.status().is2xxSuccessful());
//
//
//
//    }
//
//    @Test
//    public void excludeCMIDTest() throws Exception
//    {
//        /**
//         * Prepare Resources</BR>
//         */
//
//        domain.setId(classRoomURI);
//        domains.add(domain);
//        String soURI = testURI;
//        DefaultOccurrence occurrence = new DefaultOccurrence("schedule", domains,
//                "20151110T153028");
//
//        /**
//         * Test Process</BR>
//         */
//        ResultActions resultActions = getResultActions(occurrence, soURI);
//
//        /**
//         * Expect Result</BR>
//         */
//        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(
//                MockMvcResultMatchers.status().is2xxSuccessful());
//
//    }
//
//    @Test
//    public void excludeDomainTest() throws Exception
//    {
//        /**
//         * Prepare Resources</BR>
//         */
//
//        String soURI = testURI;
//        DefaultOccurrence occurrence = new DefaultOccurrence("occ",testContextID,
//                "20151110T153028");
//
//        /**
//         * Test Process</BR>
//         */
//        ResultActions resultActions = getResultActions(occurrence, soURI);
//
//        /**
//         * Expect Result</BR>
//         */
//        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(
//                MockMvcResultMatchers.status().is2xxSuccessful());
//
//
//
//
//    }
//
//    @Test
//    public void excludeTimeTest() throws Exception
//    {
//        /**
//         * Prepare Resources</BR>
//         */
//
//        domain.setId(classRoomURI);
//        domains.add(domain);
//        String soURI = testURI;
//        DefaultOccurrence occurrence = new DefaultOccurrence("occ",testContextID, domains);
//
//        /**
//         * Test Process</BR>
//         */
//        ResultActions resultActions = getResultActions(occurrence, soURI);
//
//        /**
//         * Expect Result</BR>
//         */
//        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(
//                MockMvcResultMatchers.status().is2xxSuccessful());
//
//    }
//
//
//    @Test
//    public void timeDifferentTest() throws Exception
//    {
//        /**
//         * Prepare Resources</BR>
//         */
//
//        domain.setId(classRoomURI);
//        domains.add(domain);
//        String soURI = testURI;
//        DefaultOccurrence occurrence = new DefaultOccurrence("occ",testContextID, domains,"20301110T153028");
//
//        /**
//         * Test Process</BR>
//         */
//        ResultActions resultActions = getResultActions(occurrence, soURI);
//
//        /**
//         * Expect Result</BR>
//         */
//        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(
//                MockMvcResultMatchers.status().is2xxSuccessful());
//
//    }
}
