package com.example.servicefeign.Hysrixcomsumer.service;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//构建一个请求合并的hystrix command
public class RequestCollapser extends HystrixCollapser<List<int[]>, int[], int[]> {
    private static final Logger logger = Logger.getLogger("com.example.servicefeign.Hysrixcomsumer.service");
    private FeignInterface fi;
    private int[] reqArg;

    public RequestCollapser(FeignInterface fi, int[] reqArg) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("requestCollapser")));
        this.fi = fi;
        this.reqArg = reqArg;
    }

    @Override
    public int[] getRequestArgument() {  //获取被合并的单个请求参数
        return this.reqArg;
    }

    @Override
    protected HystrixCommand<List<int[]>> createCommand(Collection<CollapsedRequest<int[], int[]>> collection) {
        //用来生成进行批量请求的命令
        List<int[]> reqArgs = collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList());
        return new InstanceBatchCommand(reqArgs);
    }

    @Override
    protected void mapResponseToRequests(List<int[]> batchResp, Collection<CollapsedRequest<int[], int[]>> collection) {
        //将批量请求的结果与合并的请求进行匹配以返回对应的请求结果
        int count = 0;
        for (CollapsedRequest<int[], int[]> request : collection) {
            request.setResponse(batchResp.get(count++));
        }

    }

    private final class InstanceBatchCommand extends HystrixCommand<List<int[]>> {
        private List<int[]> reqArgs;

        public InstanceBatchCommand (List<int[]> reqArgs) {
            super(HystrixCommandGroupKey.Factory.asKey("instanceBatchCommand"));
            this.reqArgs = reqArgs;
        }

        @Override
        protected List<int[]> run() throws Exception {
            List<int[]> ret = new ArrayList<>();
            logger.info("start batch!");
            for (int[] arg : reqArgs) {
                ret.add(fi.swap(arg));
            }
            return ret;
        }
    }
}
