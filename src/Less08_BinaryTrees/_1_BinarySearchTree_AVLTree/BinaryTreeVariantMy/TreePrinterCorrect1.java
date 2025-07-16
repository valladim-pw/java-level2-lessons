package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTreeVariantMy;

//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//private class TreePrinter {
//		private List<String> printList;
//		private LinkedList<Integer> levelList;
//		private int maxValueLength;
//		private int maxStringLength;
//
//		public void printTree(Node root) {
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
//		private int getTreeMaxLevel(Node root) {
//			int maxLevel = 0;
//			int levelIndex = 0;
//			String maxValueStr = "";
//			if (root == null) {
//				maxLevel = -1;
//				return maxLevel;
//			}
//			List<String> tempList = new ArrayList<>();
//			levelList = new LinkedList<>();
//			Queue<Node> queue = new LinkedList<>();
//			queue.add(root);
//			boolean isEmptyRow = true;
//
//			while (!queue.isEmpty()) {
//				int levelSize = queue.size();
//				levelList.add(levelSize);
//				for (Node node : queue) {
//					if (node != null) {
//						isEmptyRow = false;
//						break;
//					}
//				}
//				if (!isEmptyRow) {
//					for (int i = 0; i < levelSize; i++) {
//						Node currentNode = queue.poll();
//						if (currentNode != null) {
//							tempList.add(currentNode.toString());
//							maxValueStr = currentNode.toString();
//							if (maxValueStr.length() >= maxValueLength)
//								maxValueLength = maxValueStr.length();
//							if (currentNode.getLeftChild() != null) {
//								queue.add(currentNode.getLeftChild());
//							} else
//								queue.add(null);
//							if (currentNode.getRightChild() != null) {
//								queue.add(currentNode.getRightChild());
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
//			maxStringLength = (maxValueLength + 1) * levelIndex;
//			return maxLevel;
//		}
//
//		private List<String> adjustValues(int maxValueLength, List<String> values) {
//			String gapDash = "_";
//			String gapSpace = " ";
//			int mark = values.indexOf("flag");
//			int countLastRow = 1;
//			int startSpace = 0;
//			int endSpace = 0;
//			int lengthDiff = 0;
//			String value = "";
//			String newValue = "";
//
//			List<String> changeValues = new ArrayList<>();
//			for (int i = 0; i < values.size(); i++) {
//				value = values.get(i);
//				lengthDiff = maxValueLength - value.length();
//				startSpace = (int) Math.floor(((lengthDiff) * 1.0) / 2);
//				endSpace = (int) Math.ceil(((lengthDiff) * 1.0) / 2);
//
//				if (!value.equals("flag")) {
//					if (i < mark) {
//						newValue = gapDash.repeat(startSpace) + value + gapDash.repeat(endSpace);
//					}
//					if (i > mark) {
//						if (countLastRow == 1)
//							newValue = gapSpace.repeat(2) + value + gapSpace.repeat(endSpace + startSpace);
//						else {
//							if (countLastRow % 2 == 0)
//								newValue = gapSpace.repeat((endSpace + startSpace) >= 2 ? (endSpace + startSpace) - (value.length() < 4 && maxValueLength > 4 ? 2 : 0) : (endSpace + startSpace))
//			           + value + gapSpace.repeat(value.length() < 4 && maxValueLength > 4 ? 4 : 2);
//							else
//								newValue = value + gapSpace.repeat(endSpace + startSpace);
//						}
//						countLastRow++;
//					}
//					changeValues.add(newValue);
//				}
//			}
//			return changeValues;
//		}
//
//		private void printRow(int space, List<String> list, int index) {
//			String gapSpace = " ";
//			String gapDash = "_";
//			int startValueHalf = (int) Math.floor(((maxValueLength) * 1.0) / 2);
//			int endValueHalf = (int) Math.ceil(((maxValueLength) * 1.0) / 2);
//
//			int startCount = ((maxStringLength / (index * 2) - space) + space) - startValueHalf;
//			int tmpStartSpaceCount = (int) Math.ceil(((startCount) * 1.0) / 2);
//			int tmpStartDashCount = (int) Math.floor(((startCount) * 1.0) / 2);
//			int startDashCount = tmpStartDashCount > startValueHalf ? tmpStartDashCount - startValueHalf : tmpStartDashCount;
//			int startSpaceCount = tmpStartDashCount > startValueHalf ? tmpStartSpaceCount + startValueHalf : tmpStartSpaceCount;
//
//			int endCount = ((maxStringLength / (index * 2) - space) + space) - endValueHalf;
//			int tmpEndDashCount = (int) Math.ceil(((endCount) * 1.0) / 2);
//			int tmpEndSpaceCount = (int) Math.floor(((endCount) * 1.0) / 2);
//			int endDashCount = tmpEndDashCount > endValueHalf ? tmpEndDashCount - endValueHalf : tmpEndDashCount;
//			int endSpaceCount = tmpEndDashCount > endValueHalf ? tmpEndSpaceCount + endValueHalf : tmpEndSpaceCount;
//
//			LinkedList<String> valueList = new LinkedList<>(list);
//			String startSpaceHalf = "";
//			String startDashHalf = "";
//			String endSpaceHalf = "";
//			String endDashHalf = "";
//
//			while (!valueList.isEmpty()) {
//				String value = valueList.poll();
//
//				startSpaceHalf = gapSpace.repeat(startSpaceCount);
//				endSpaceHalf = gapSpace.repeat(endSpaceCount);
//				startDashHalf = gapDash.repeat(startDashCount);
//				endDashHalf = gapDash.repeat(endDashCount);
//
//				StringBuilder sb = new StringBuilder();
//				if (levelList.size() > 1)
//					sb.append(startSpaceHalf + startDashHalf + value + endDashHalf + endSpaceHalf);
//				else
//					sb.append(value);
//				System.out.print(sb.toString());
//			}
//			System.out.println();
//		}
//	}