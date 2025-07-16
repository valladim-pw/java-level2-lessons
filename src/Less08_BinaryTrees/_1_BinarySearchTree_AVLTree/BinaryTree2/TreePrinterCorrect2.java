package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTree2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//private class TreePrinter {
//		private List<String> printList;
//		private LinkedList<Integer> levelList;
//		private int maxValueLength;
//		private int maxStringLength;
//
//		public void printTree(TreeLeaf<K, V> root) {
//			this.maxValueLength = Integer.MIN_VALUE;
//			this.maxStringLength = 0;
//			int space = getTreeMaxLevel(root);
//			List<String> subList = new ArrayList<>();
//			System.out.println();
//			System.out.println("---New Tree--------------");
//			if (space < 0) {
//				System.out.println(" () ");
//				return;
//			}
//
//			while (!printList.isEmpty()) {
//				int index = levelList.poll();
//				subList = printList.subList(0, index);
//				printRow(space, subList, subList.size());
//				printList = printList.subList(index, printList.size());
//				space /= 2;
//			}
//		}
//
//		private int getTreeMaxLevel(TreeLeaf<K, V> root) {
//			int maxLevel = 0;
//			int levelIndex = 0;
//			String maxValueStr = "";
//			if (root == null) {
//				maxLevel = -1;
//				return maxLevel;
//			}
//			List<String> tempList = new ArrayList<>();
//			levelList = new LinkedList<>();
//			Queue<TreeLeaf> queue = new LinkedList<>();
//			queue.add(root);
//			boolean isEmptyRow = true;
//
//			while (!queue.isEmpty()) {
//				int levelSize = queue.size();
//				levelList.add(levelSize);
//				for (TreeLeaf node : queue) {
//					if (node != null) {
//						isEmptyRow = false;
//						break;
//					}
//				}
//				if (!isEmptyRow) {
//					for (int i = 0; i < levelSize; i++) {
//						TreeLeaf current = queue.poll();
//						if (current != null) {
//							tempList.add(current.toString());
//							maxValueStr = current.toString();
//							if (maxValueStr.length() >= maxValueLength)
//								maxValueLength = maxValueStr.length();
//							if (current.left != null) {
//								queue.add(current.left);
//							} else
//								queue.add(null);
//							if (current.right != null) {
//								queue.add(current.right);
//							} else
//								queue.add(null);
//						} else {
//							tempList.add("()");
//							queue.add(null);
//							queue.add(null);
//						}
//					}
//					levelIndex = levelSize;
//					isEmptyRow = true;
//				} else {
//					maxLevel = levelSize;
//					break;
//				}
//			}
//			int index = tempList.size() - maxLevel / 2;
//			tempList.add(index, "flag");
//			printList = adjustValues(maxValueLength, tempList);
//			maxStringLength = maxValueLength * levelIndex;
//
//			return maxLevel;
//		}
//
//		private List<String> adjustValues(int maxValueLength, List<String> values) {
//			String gapDash = "_";
//			String gapSpace = "|";
//			int mark = values.indexOf("flag");
//			int startSpace = 0;
//			int endSpace = 0;
//			int lengthDiff = 0;
//			String value = "";
//			String newValue = "";
//
//			List<String> changeValues = new ArrayList<>();
//			for (int i = 0; i < values.size(); i++) {
//				value = values.get(i);
//
//				lengthDiff = maxValueLength - value.length();
//
//				startSpace = (int) Math.floor(((lengthDiff) * 1.0) / 2);
//				endSpace = (int) Math.ceil(((lengthDiff) * 1.0) / 2);
//
//				if (!value.equals("flag")) {
//					if (lengthDiff >= 0) {
//						if (i < mark)
//							newValue = gapDash.repeat(startSpace) + value + gapDash.repeat(endSpace);
//						if (i > mark)
//							newValue = gapSpace.repeat(startSpace) + value + gapSpace.repeat(endSpace);
//
//						changeValues.add(newValue);
//					} else
//						changeValues.add(value);
//				}
//			}
//			return changeValues;
//		}
//
//		private void printRow(int space, List<String> list, int index) {
//
////			System.out.println("levelList: " + levelList);
////			System.out.println("index: " + index);
////			System.out.println("space: " + space);
////			System.out.println("maxValueLength: " + maxValueLength);
////			System.out.println("maxStringLength: " + maxStringLength);
//
//			int count = 0;
//			String gapSpace = "|";
//			String gapDash = "_";
//			int startValueHalf = 0;
//			int endValueHalf = 0;
//			int startCount = 0;
//			int spaceCount = 0;
//			int startDashCount = 0;
//			int endDashCount = 0;
//
//			startValueHalf = (int) Math.floor(((maxValueLength) * 1.0) / 2);
//			endValueHalf = (int) Math.ceil(((maxValueLength) * 1.0) / 2);
//
//			startCount = (maxStringLength / (index * 2));
//
//			spaceCount = startCount / 2;
//			if (spaceCount >= startValueHalf)
//				startDashCount = spaceCount - startValueHalf;
//			if (spaceCount >= endValueHalf)
//				endDashCount = spaceCount - endValueHalf;
//
//			LinkedList<String> valueList = new LinkedList<>(list);
//			String spaceHalf = "";
//			String startDashHalf = "";
//			String endDashHalf = "";
//
//			while (!valueList.isEmpty()) {
//				String value = valueList.poll();
//				if (space == 4 && maxValueLength % 2 != 0)
//					count++;
//
//				if (count % 2 == 0 && count != 0)
//					spaceHalf = gapSpace.repeat(spaceCount + 1);
//				else
//					spaceHalf = gapSpace.repeat(spaceCount);
//
//				startDashHalf = gapDash.repeat(startDashCount);
//				endDashHalf = gapDash.repeat(endDashCount);
//
//				StringBuilder sb = new StringBuilder(space);
//
//				if (levelList.size() > 1)
//					sb.append(spaceHalf + startDashHalf + value + endDashHalf + spaceHalf);
//				else
//					sb.append(value);
//				System.out.print(sb.toString());
//			}
//			System.out.println();
//		}
//	}