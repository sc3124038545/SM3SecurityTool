import MonobitTest
import FrequencyWithinBlockTest
import RunsTest
import LongestRunOnesInBlockTest
import BinaryMatrixRankTest
import DFTTest
import MaurersUniversalTest
import LinearComplexityTest
import SerialTest
import ApproximateEntropyTest
import CumulativeSumsTest
import PukeTest
import RunsDistributionTest
import BinaryDerivativeTest
import AutocorrelationTest
import sys

results = list()
def aa(str1):
        bits = list(map(int, str1))
        print('单比特频率测试：')
        success,p,plist=MonobitTest.monobit_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)

            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('单比特频率测试',summary_p, summary_result))

        print('块内频数测试：')
        (success,p,plist) =FrequencyWithinBlockTest.frequency_within_block_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('块内频数测试',summary_p, summary_result))


        print('游程总数测试：')
        (success,p,plist) =RunsTest.runs_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('游程总数测试',summary_p, summary_result))


        print('块内最长1游程测试：')
        (success,p,plist) =LongestRunOnesInBlockTest.longest_run_ones_in_a_block_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('块内最长1游程测试',summary_p, summary_result))


        print('矩阵秩测试:')
        (success,p,plist) =BinaryMatrixRankTest.binary_matrix_rank_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('矩阵秩测试',summary_p, summary_result))


        print('离散傅里叶变换测试：')
        (success,p,plist) =DFTTest.dft_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('离散傅里叶变换测试',summary_p, summary_result))


        print('Maurer通用统计测试：')
        (success,p,plist) =MaurersUniversalTest.maurers_universal_test(bits,2,4)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('Maurer通用统计测试',summary_p, summary_result))


        print('线性复杂度测试：')
        (success,p,plist) =LinearComplexityTest.linear_complexity_test(bits,7)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('线性复杂度测试',summary_p, summary_result))


        print('重叠子序列测试：')
        (success,p,plist) =SerialTest.serial_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('重叠子序列测试',summary_p, summary_result))


        print('近似熵测试：')
        (success,p,plist) =ApproximateEntropyTest.approximate_entropy_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            summary_p = str(min(plist))
        results.append(('近似熵测试',summary_p, summary_result))

        
        print('累积和测试：')
        (success,p,plist) =CumulativeSumsTest.cumulative_sums_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            print('  p = ', str(min(plist)))
            summary_p = str(min(plist))
        results.append(('累积和测试',summary_p, summary_result))

       
        print('扑克测试：')
        (success,p,plist) =PukeTest.puke_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            print('p=', str(min(plist)))
            summary_p = str(min(plist))
        results.append(('扑克测试',summary_p, summary_result))
    
        
        print('游程分布测试：')
        (success,p,plist) =RunsDistributionTest.runs_distribution_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            print('p=', str(min(plist)))
            summary_p = str(min(plist))
        results.append(('游程分布测试',summary_p, summary_result))
        
        print('二元推导测试：')
        (success,p,plist) =BinaryDerivativeTest.binary_derivative_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            print('p=', str(min(plist)))
            summary_p = str(min(plist))
        results.append(('二元推导测试',summary_p, summary_result))
        
        print('自相关测试：')
        (success,p,plist) =AutocorrelationTest.autocorrelation_test(bits)
        if success:
            summary_result = "通过"
        else:
            summary_result = "不通过"
        
        if p != None:
            print ("  P="+str(p))
            summary_p = str(p)
            
        if plist != None:
            print('p=', str(min(plist)))
            summary_p = str(min(plist))
        results.append(('自相关测试',summary_p, summary_result))
        
import re
def len_zh(data):
    temp = re.findall('[^a-zA-Z0-9.]+', data)
    count = 0
    for i in temp:
        count += len(i)
    return(count)

bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 
if __name__ == "__main__":
    aa(sys.argv[1])
    #aa(bits)
    print('**************************************************************************')
    for result in results:
        (summary_name,summary_p, summary_result) = result
        zh = len_zh(summary_name)
        print (summary_name.ljust(30-zh),summary_p.ljust(28),summary_result)
    
