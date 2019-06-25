package com.atguigu.mapreduce.distributedcache;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DistributedDriver {

	public static void main(String[] args) throws Exception {

		// 1 ��ȡjob��Ϣ
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		// 2 ���ü���jar��·��
		job.setJarByClass(DistributedDriver.class);

		// 3 ����map
		job.setMapperClass(DistributedMapper.class);

		// 4 �������������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// 5 �����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// 6 ���ػ�������
//		job.addCacheFile(new URI("file:/e:/cache/pd.txt"));
		job.addCacheFile(new URI("file:/e:/inputinputformat/inputtable/pd.txt"));
		
		// 7 map��join���߼�����Ҫreduce�׶Σ�����reducetask����Ϊ0
		job.setNumReduceTasks(0);

		// 8 �ύ
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);

	}
}